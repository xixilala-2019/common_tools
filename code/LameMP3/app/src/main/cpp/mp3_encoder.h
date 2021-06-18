//
// Created by AYL_iwalk on 21/6/15.
//





#include "libmp3lame/lame.h"
#include <stdio.h>

#ifndef LAMEMP3_MP3_ENCODER_H
#define LAMEMP3_MP3_ENCODER_H

#endif //LAMEMP3_MP3_ENCODER_H


class Mp3Encoder {
    private:
        FILE* pcmFile{};
        FILE* mp3File{};
        lame_t lameClient{};
public:
    Mp3Encoder();

    int Init(const char* pcmFilePath, const char *mp3FilePath, int sampleRate, int channels, int bitRate);
    void Encode();
    void Destory();


};