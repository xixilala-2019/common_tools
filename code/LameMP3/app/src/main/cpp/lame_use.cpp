#include <jni.h>
#include <string>
#include <iostream>
#include <iosfwd>
//
// Created by AYL_iwalk on 21/6/7.
//

#include "libmp3lame/lame.h"
#include "mp3_encoder.h"

lame_global_flags *gfp;

unsigned char *buffer;
int bufferSize = 1024*2;
FILE *pFile;


extern "C"
JNIEXPORT jstring JNICALL
Java_com_xixilala_lamemp3_Mp3Encode_lameVersion(JNIEnv *env, jobject thiz) {
    std::string hello = "lame version is ";
    hello += get_lame_version();
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xixilala_lamemp3_Mp3Encode_initLame(JNIEnv *env, jobject thiz, jint channels,
                                             jint sample_rate, jint brate, jint mode,
                                             jint quality, jstring mp3_file_path) {
//    std::string mp3FilePath;
//    mp3FilePath = env->GetStringUTFChars(mp3_file_path, NULL);

//    const char *path = mp3FilePath.c_str();

//    pFile = fopen(path, "a+");



//    gfp = lame_init();
//    lame_set_num_channels(gfp, channels);
//    lame_set_in_samplerate(gfp,sample_rate);
//    lame_set_brate(gfp,brate);
//    lame_set_mode(gfp,MONO);
//    lame_set_quality(gfp,quality);   /* 2=high  5 = medium  7=low */

//    lame_set_num_channels(gfp, 2);
//    lame_set_in_samplerate(gfp,44100);
//    lame_set_brate(gfp,16);
//    lame_set_mode(gfp,MONO);
//    lame_set_quality(gfp,quality);   /* 2=high  5 = medium  7=low */

    std::int32_t ret_code = lame_init_params(gfp);
//    Mp3Encoder* mp3Encoder = new Mp3Encoder();
//    mp3Encoder->Init()


    return ret_code;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xixilala_lamemp3_Mp3Encode_encode(JNIEnv *env, jobject thiz, jshortArray left_pcm,
                                           jshortArray right_pcm,
                                           jint num_sample) {

    int mp3DataSize = num_sample;
    unsigned char mp3Buffer[mp3DataSize];
    FILE* m;

//    lame_encode_buffer()
    lame_encode_buffer(gfp,
            env->GetShortArrayElements(left_pcm,NULL),
            env->GetShortArrayElements(right_pcm,NULL),
                       num_sample,
                       mp3Buffer,
                       mp3DataSize
                       );

    //写文件
//    fputc(buffer,pFile)

//    int ch = 67;
//    fputc(ch, pFile);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xixilala_lamemp3_Mp3Encode_encodeDone(JNIEnv *env, jobject thiz) {
    // TODO: implement encodeDone()
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xixilala_lamemp3_Mp3Encode_releaseLame(JNIEnv *env, jobject thiz) {
    fclose(pFile);
    lame_close(gfp);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xixilala_lamemp3_Mp3Encode_encodeMp3(JNIEnv *env, jobject thiz, jstring pcm_file_path,
                                              jstring mp3_file_path, jint sample_rate,
                                              jint channels, jint bit_rate) {
    const char *pcmFile = env->GetStringUTFChars(pcm_file_path, NULL);
    const char *mp3File = env->GetStringUTFChars(mp3_file_path, NULL);
    Mp3Encoder* mp3Encoder = new Mp3Encoder();
    mp3Encoder->Init(pcmFile,mp3File,sample_rate,channels,bit_rate);
    mp3Encoder->Encode();
    mp3Encoder->Destory();

    env->ReleaseStringUTFChars(pcm_file_path, pcmFile);
    env->ReleaseStringUTFChars(mp3_file_path, mp3File);
}








