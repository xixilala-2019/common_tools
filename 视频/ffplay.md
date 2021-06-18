1.ffplay

```
-查看视频metadata
Z:\9_ffmpeg\ffmpeg\bin\ffplay Z:\9_ffmpeg\videos\big_buck_bunny.mp4

弹出播放窗口
Input #0, mov,mp4,m4a,3gp,3g2,mj2, from 'Z:\9_ffmpeg\videos\big_buck_bunny.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    creation_time   : 2010-02-09T01:55:39.000000Z
  Duration: 00:01:00.10, start: 0.000000, bitrate: 733 kb/s
  Stream #0:0(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 65 kb/s (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Sound Media Handler
      vendor_id       : [0][0][0][0]
  Stream #0:1(eng): Video: h264 (Constrained Baseline) (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709), 640x360, 612 kb/s, 23.96 fps, 24 tbr, 600 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
  Stream #0:2(eng): Data: none (rtp  / 0x20707472), 45 kb/s
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : hint media handler
  Stream #0:3(eng): Data: none (rtp  / 0x20707472), 5 kb/s
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : hint media handler
```

````
-loop 10 结束后从头播放 循环10次
-ast  1  播放第一路音频，如果音频流不存在，静音播放
-vst  1  第一路视频流，如果不存在就是黑屏
````

```
-播放PCM
Z:\9_ffmpeg\ffmpeg\bin\ffplay song.pcm -f s16le -channels 2 -ar 44100

后边的参数必须设置正确，不然不是正确的播放结果
-f			格式
-channels	 声道数
-ar			采样率
```

```
-一帧视频帧的播放
Z:\9_ffmpeg\ffmpeg\bin\ffplay -f rawvideo -pixel_format yuv420p -s 640*360 texture.yuv
-f				格式
-pixel_format	 表示格式（yuv420p，rgb24）
-s				宽高
音视频同步
-sync audio		音频为基准
	  video		视频为基准
	  ext		外部时钟为基准
```







