package com.xixilala.lamemp3

import android.content.Context
import android.media.*
import android.media.AudioAttributes.CONTENT_TYPE_MUSIC
import android.media.AudioFormat.CHANNEL_IN_MONO
import android.media.AudioFormat.ENCODING_PCM_16BIT
import android.media.AudioTrack.MODE_STREAM
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


/**
 * 1.录制PCM格式音频文件
 * 2.播放PCM
 * 3.转PCM为MP3格式
 * 4.播放MP3
 */
class MainActivity : AppCompatActivity(),LameMP3 {

    val pcmFileName = "test.pcm"
    val mp3FileName = "test.mp3"

    val filePath = lazy { LAPP.context.cacheDir }

    val pcmFile = File(filePath.value,pcmFileName)
    val mp3File = File(filePath.value,mp3FileName)

    val sampleRate = 441_00

    var recording = false



    val mp3Encode = Mp3Encode()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mediaRecord  = MediaRecorder()


        findViewById<View>(R.id.btnRecordPCM).setOnClickListener { recordPCM() }
        findViewById<View>(R.id.btnStopRecordPCM).setOnClickListener { recording = false }
        findViewById<View>(R.id.btnPlayPCM).setOnClickListener { playPCM() }
        findViewById<View>(R.id.btnTrans2Mp3).setOnClickListener { transPCM2MP3() }
        findViewById<View>(R.id.btnPlayMP3).setOnClickListener { playMP3() }
        findViewById<View>(R.id.btnEncode).setOnClickListener { fileEncode() }



        findViewById<View>(R.id.btnLoadLameVersion).setOnClickListener { Toast.makeText(application,mp3Encode.lameVersion(),Toast.LENGTH_SHORT).show() }


    }

    private fun fileEncode() {

        GlobalScope.launch {
            withContext(Dispatchers.IO) {

                if (mp3File.exists()) {
                    mp3File.delete();
                }
                mp3File.createNewFile()

                val startTime = System.currentTimeMillis()
                mp3Encode.encodeMp3(pcmFile.absolutePath, mp3File.absolutePath, sampleRate, 2, 1024*10 )
                Log.e("cost time"," ${System.currentTimeMillis()-startTime}")


            }
        }


    }

    override fun recordPCM() {

        /*
        val audioAttributes = AudioAttributes.Builder()
                .setFlags(AudioAttributes.USAGE_MEDIA)
                .setContentType(CONTENT_TYPE_MUSIC)
                .build()
        val audioFormat = AudioFormat.Builder()
                .setSampleRate(recordSampleRate)
                .setEncoding(ENCODING_PCM_16BIT)
                .build()


        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val audioTrack = AudioTrack(audioAttributes,audioFormat, recordSampleRate, MODE_STATIC,audioManager.generateAudioSessionId())
//        val audioTrack = AudioTrack(AudioManager.STREAM_MUSIC, recordSampleRate, AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT, )
        audio*/



        recording = true




        GlobalScope.launch {

            withContext(Dispatchers.IO) {

                val minBufferSize = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
//使用 AudioRecord 去录音
                val audioRecord =   AudioRecord(
                        MediaRecorder.AudioSource.MIC,
                    sampleRate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                minBufferSize
                );


                val buffer = ByteArray(minBufferSize)
                if (pcmFile.exists()) {
                    pcmFile.delete()
                    pcmFile.createNewFile()
                }

                audioRecord.startRecording()

                val outputStreamWriter = (FileOutputStream(pcmFile))


                while(recording) {
                    val size = audioRecord.read(buffer, 0, buffer.size)
                    if (size > 0) {
                        outputStreamWriter.write(buffer, 0, size)
                    }
                }
                audioRecord.stop()
                audioRecord.release()

                outputStreamWriter.flush()

                outputStreamWriter.close()

            }
        }
    }

    override fun playPCM() {

        GlobalScope.launch {

            withContext(Dispatchers.IO) {

                val audioAttributes = AudioAttributes.Builder()
                        .setFlags(AudioAttributes.USAGE_MEDIA)
                        .setContentType(CONTENT_TYPE_MUSIC)
                        .build()
                val audioFormat = AudioFormat.Builder()
                        .setSampleRate(sampleRate)
                        .setEncoding(ENCODING_PCM_16BIT)
                        .build()


                val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
                val audioTrack = AudioTrack(audioAttributes,audioFormat, sampleRate, MODE_STREAM,audioManager.generateAudioSessionId())


                val byteArray = ByteArray(1024 * 4)
                val fileInputStream = FileInputStream(pcmFile)
                var i = 0
                try {

                    while ({i = fileInputStream.read(byteArray);i}() != -1) {
                        audioTrack.write(byteArray,0, i)

                        audioTrack.play()
                    }
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun transPCM2MP3() {
        val returnCode = mp3Encode.initLame(1, 1, 2, 1, 5, mp3File.absolutePath)
        Log.e("mp3","lame return code =$returnCode")

        GlobalScope.launch(Dispatchers.IO) {

            val byteArray = ByteArray(1024 * 4)
            val fileInputStream = FileInputStream(pcmFile)
            var i = 0
            try {

                while ({i = fileInputStream.read(byteArray);i}() != -1) {
                    mp3Encode.encode(byteArray2ShortArray(byteArray),byteArray2ShortArray(byteArray), i)
                }
                mp3Encode.encodeDone()
                mp3Encode.releaseLame()
            } catch (e:Exception) {
                e.printStackTrace()
            }



        }


    }

    override fun playMP3() {
        val mp3Player = MediaPlayer()
        mp3Player.setDataSource(mp3File.absolutePath)
        mp3Player.prepare()
        mp3Player.setOnPreparedListener {
            it.start()
        }
    }



    fun byteArray2ShortArray(byteArray: ByteArray):ShortArray {
        return ShortArray(byteArray.size / 2) {
            (byteArray[it * 2] + (byteArray[(it * 2) + 1].toInt() shl 8)).toShort()
        }
    }

    fun toByteArray(src: ShortArray): ByteArray? {
        val count = src.size
        val dest = ByteArray(count shl 1)
        for (i in 0 until count) {
            dest[i * 2] = src[i].toByte()
            dest[i * 2 + 1] = (src[i].toInt() shr(8)) as Byte
        }
        return dest
    }

}