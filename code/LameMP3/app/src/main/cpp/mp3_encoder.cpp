//
// Created by AYL_iwalk on 21/6/15.
//


#include "mp3_encoder.h"

int Mp3Encoder::Init(const char *pcmFilePath, const char *mp3FilePath, int sampleRate, int channels, int bitRate) {
    int ret = -1;
    pcmFile = fopen(pcmFilePath,"rb");
    if (pcmFile) {
        mp3File = fopen(mp3FilePath,"wb");
        if (mp3File) {
            lameClient = lame_init();
            lame_set_in_samplerate(lameClient,sampleRate);
            lame_set_out_samplerate(lameClient, sampleRate);
            lame_set_num_channels(lameClient, channels);
            lame_set_brate(lameClient, bitRate/1000);
            lame_init_params(lameClient);
            ret = 0;
        }
    }
    return ret;
}

void Mp3Encoder::Encode() {
    int bufferSize = 1024 * 16;
    auto* buffer = new short [bufferSize/2];
    auto* leftBuffer = new short [bufferSize/4];
    auto* rightBuffer = new short [bufferSize/4];
    auto* mp3Buffer = new unsigned char [bufferSize];
    size_t readBufferSize;
    while((readBufferSize = fread(buffer,2,bufferSize/2,pcmFile)) > 0) {
        for (int i=0; i < bufferSize ; i++) {
            if (i%2==0) {
                leftBuffer[i/2] = buffer[i];
            } else {
                rightBuffer[i/2] = buffer[i];
            }
        }
        size_t  wroteSize = lame_encode_buffer(lameClient, (short int* ) leftBuffer, (short int*) rightBuffer, (int)(readBufferSize/2), mp3Buffer, bufferSize);
        fwrite(mp3Buffer, 1,wroteSize, mp3File);
    }
    delete[] buffer;
    delete[] leftBuffer;
    delete[] rightBuffer;
    delete[] mp3Buffer;
}

void Mp3Encoder::Destory() {
    if (pcmFile) {
        fclose(pcmFile);
    }
    if (mp3File) {
        fclose(mp3File);
        lame_close(lameClient);
    }
}

Mp3Encoder::Mp3Encoder() = default;

