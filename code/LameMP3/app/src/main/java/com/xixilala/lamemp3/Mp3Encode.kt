package com.xixilala.lamemp3

/**
 * https://blog.csdn.net/qq_34829270/article/details/102548797
 * https://www.cnblogs.com/yuanxiaoping_21cn_com/p/5634051.html
 */
class Mp3Encode {

    companion object {
        init {
            System.loadLibrary("mp3Lame")
        }
    }

    external fun lameVersion():String

    /**
     * 初始化lame
     * @param channels 声道数
     * @param sampleRate 采样率
     * @param brate
     * @param mode 模式
     * @param quality 质量
     */
    external fun initLame(channels:Int,sampleRate:Int,brate:Int,mode:Int,quality:Int,mp3FilePath:String):Int

    external fun encode(leftPcm:ShortArray,rightPcm:ShortArray, numSample:Int)

    external fun encodeDone()

    external fun releaseLame()

    external fun encodeMp3(pcmFilePath:String, mp3FilePath:String, sampleRate: Int, channels: Int, bitRate:Int)
}