1.ffprobe

```
-查看视频metadata
Z:\9_ffmpeg\ffmpeg\bin\ffprobe Z:\9_ffmpeg\videos\big_buck_bunny.mp4

Input #0, mov,mp4,m4a,3gp,3g2,mj2, from 'Z:\9_ffmpeg\videos\big_buck_bunny.mp4':
  Metadata:
    major_brand     : mp42 主要品牌
    minor_version   : 1 次要版本
    compatible_brands: mp42avc1 兼容品牌
    creation_time   : 2010-02-09T01:55:39.000000Z
  Duration: 00:01:00.10, start: 0.000000, bitrate: 733 kb/s 时长一分钟，开始时间0，比特率733kb/s
  Stream #0:0(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 65 kb/s (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Sound Media Handler 处理程序名称
      vendor_id       : [0][0][0][0] 供应商ID
      音频流，aac格式(封装格式是mp4a)，并且采用Profile是LC规格，采样率22050HZ，立体声，数据表示格式为浮点型，比特率65kb/s
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
     视频流，编码方式是H264(封装格式似乎AVC1)，每一帧的数据表示是YUV420P的格式，分辨率是640x360，比特率是612kb/s，帧率是 23.96，
Unsupported codec with id 0 for input stream 2
Unsupported codec with id 0 for input stream 3
```

```
-输出格式信息
Z:\9_ffmpeg\ffmpeg\bin\ffprobe -show_format Z:\9_ffmpeg\videos\big_buck_bunny.mp4

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
Unsupported codec with id 0 for input stream 2
Unsupported codec with id 0 for input stream 3
[FORMAT]
filename=Z:\9_ffmpeg\videos\big_buck_bunny.mp4
nb_streams=4									输入视频的AVStream 个数
nb_programs=0									
format_name=mov,mp4,m4a,3gp,3g2,mj2				  格式名称
format_long_name=QuickTime / MOV				  格式完整名称
start_time=0.000000								 开始时间
duration=60.095000
size=5510872
bit_rate=733621									 
probe_score=100									ffprobe会检测视频格式和后缀是否对照，对照得高分，最高100
TAG:major_brand=mp42
TAG:minor_version=1
TAG:compatible_brands=mp42avc1
TAG:creation_time=2010-02-09T01:55:39.000000Z
[/FORMAT]
```

```
-输出格式信息，json格式
Z:\9_ffmpeg\ffmpeg\bin\ffprobe -print_format json -show_streams Z:\9_ffmpeg\videos\big_buck_bunny.mp4

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
Unsupported codec with id 0 for input stream 2
Unsupported codec with id 0 for input stream 3
    "streams": [
        {
            "index": 0,
            "codec_name": "aac",
            "codec_long_name": "AAC (Advanced Audio Coding)",
            "profile": "LC",
            "codec_type": "audio",
            "codec_tag_string": "mp4a",
            "codec_tag": "0x6134706d",
            "sample_fmt": "fltp",
            "sample_rate": "22050",
            "channels": 2,
            "channel_layout": "stereo",
            "bits_per_sample": 0,
            "r_frame_rate": "0/0",
            "avg_frame_rate": "0/0",
            "time_base": "1/22050",
            "start_pts": 0,
            "start_time": "0.000000",
            "duration_ts": 1325095,
            "duration": "60.095011",
            "bit_rate": "65075",
            "nb_frames": "1295",
            "disposition": {			配置
                "default": 1,
                "dub": 0,
                "original": 0,
                "comment": 0,
                "lyrics": 0,
                "karaoke": 0,
                "forced": 0,
                "hearing_impaired": 0,
                "visual_impaired": 0,
                "clean_effects": 0,
                "attached_pic": 0,
                "timed_thumbnails": 0,
                "captions": 0,
                "descriptions": 0,
                "metadata": 0,
                "dependent": 0,
                "still_image": 0
            },
            "tags": {
                "creation_time": "2010-02-09T01:55:39.000000Z",
                "language": "eng",
                "handler_name": "Apple Sound Media Handler",
                "vendor_id": "[0][0][0][0]"
            }
        },
        {
            "index": 1,
            "codec_name": "h264",
            "codec_long_name": "H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10",
            "profile": "Constrained Baseline",
            "codec_type": "video",
            "codec_tag_string": "avc1",
            "codec_tag": "0x31637661",
            "width": 640,
            "height": 360,
            "coded_width": 640,
            "coded_height": 360,
            "closed_captions": 0,
            "has_b_frames": 0,
            "pix_fmt": "yuv420p",
            "level": 30,
            "color_range": "tv",
            "color_space": "smpte170m",
            "color_transfer": "bt709",
            "color_primaries": "smpte170m",
            "chroma_location": "topleft",
            "refs": 1,
            "is_avc": "true",
            "nal_length_size": "4",
            "r_frame_rate": "24/1",
            "avg_frame_rate": "288000/12019",
            "time_base": "1/600",
            "start_pts": 0,
            "start_time": "0.000000",
            "duration_ts": 36057,
            "duration": "60.095000",
            "bit_rate": "612177",
            "bits_per_raw_sample": "8",
            "nb_frames": "1440",
            "disposition": {
                "default": 1,
                "dub": 0,
                "original": 0,
                "comment": 0,
                "lyrics": 0,
                "karaoke": 0,
                "forced": 0,
                "hearing_impaired": 0,
                "visual_impaired": 0,
                "clean_effects": 0,
                "attached_pic": 0,
                "timed_thumbnails": 0,
                "captions": 0,
                "descriptions": 0,
                "metadata": 0,
                "dependent": 0,
                "still_image": 0
            },
            "tags": {
                "creation_time": "2010-02-09T01:55:39.000000Z",
                "language": "eng",
                "handler_name": "Apple Video Media Handler",
                "vendor_id": "[0][0][0][0]"
            }
        },
        {
            "index": 2,
            "codec_type": "data",
            "codec_tag_string": "rtp ",
            "codec_tag": "0x20707472",
            "r_frame_rate": "0/0",
            "avg_frame_rate": "0/0",
            "time_base": "1/90000",
            "start_pts": 0,
            "start_time": "0.000000",
            "duration_ts": 5408550,
            "duration": "60.095000",
            "bit_rate": "45858",
            "nb_frames": "1440",
            "disposition": {
                "default": 0,
                "dub": 0,
                "original": 0,
                "comment": 0,
                "lyrics": 0,
                "karaoke": 0,
                "forced": 0,
                "hearing_impaired": 0,
                "visual_impaired": 0,
                "clean_effects": 0,
                "attached_pic": 0,
                "timed_thumbnails": 0,
                "captions": 0,
                "descriptions": 0,
                "metadata": 0,
                "dependent": 0,
                "still_image": 0
            },
            "tags": {
                "creation_time": "2010-02-09T01:55:39.000000Z",
                "language": "eng",
                "handler_name": "hint media handler"
            }
        },
        {
            "index": 3,
            "codec_type": "data",
            "codec_tag_string": "rtp ",
            "codec_tag": "0x20707472",
            "r_frame_rate": "0/0",
            "avg_frame_rate": "0/0",
            "time_base": "1/22050",
            "start_pts": 0,
            "start_time": "0.000000",
            "duration_ts": 1325095,
            "duration": "60.095011",
            "bit_rate": "5514",
            "nb_frames": "648",
            "disposition": {
                "default": 0,
                "dub": 0,
                "original": 0,
                "comment": 0,
                "lyrics": 0,
                "karaoke": 0,
                "forced": 0,
                "hearing_impaired": 0,
                "visual_impaired": 0,
                "clean_effects": 0,
                "attached_pic": 0,
                "timed_thumbnails": 0,
                "captions": 0,
                "descriptions": 0,
                "metadata": 0,
                "dependent": 0,
                "still_image": 0
            },
            "tags": {
                "creation_time": "2010-02-09T01:55:39.000000Z",
                "language": "eng",
                "handler_name": "hint media handler"
            }
        }
    ]
}
```

```
-显示帧信息（每一帧）
Z:\9_ffmpeg\ffmpeg\bin\ffprobe -show_frames Z:\9_ffmpeg\videos\big_buck_bunny.mp4

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
Unsupported codec with id 0 for input stream 2
Unsupported codec with id 0 for input stream 3
[FRAME]
media_type=audio
stream_index=0
key_frame=1
pkt_pts=0
pkt_pts_time=0.000000
pkt_dts=0
pkt_dts_time=0.000000
best_effort_timestamp=0
best_effort_timestamp_time=0.000000
pkt_duration=1024
pkt_duration_time=0.046440
pkt_pos=37130
pkt_size=7
sample_fmt=fltp
nb_samples=1024
channels=2
channel_layout=stereo
[/FRAME]
[FRAME]
media_type=video
stream_index=1
key_frame=0
pkt_pts=19450
pkt_pts_time=32.416667
pkt_dts=19450
pkt_dts_time=32.416667
best_effort_timestamp=19450
best_effort_timestamp_time=32.416667
pkt_duration=25
pkt_duration_time=0.041667
pkt_pos=2794619
pkt_size=2460
width=640
height=360
pix_fmt=yuv420p
sample_aspect_ratio=N/A
pict_type=P
coded_picture_number=778
display_picture_number=0
interlaced_frame=0
top_field_first=0
repeat_pict=0
color_range=tv
color_space=smpte170m
color_primaries=smpte170m
color_transfer=bt709
chroma_location=topleft
[SIDE_DATA]
side_data_type=H.26[45] User Data Unregistered SEI message
[/SIDE_DATA]
[/FRAME]
```

```
-显示包信息（每一包）
Z:\9_ffmpeg\ffmpeg\bin\ffprobe -show_packets Z:\9_ffmpeg\videos\big_buck_bunny.mp4

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
Unsupported codec with id 0 for input stream 2
Unsupported codec with id 0 for input stream 3
[PACKET]
codec_type=audio
stream_index=0
pts=0
pts_time=0.000000
dts=0
dts_time=0.000000
duration=1024
duration_time=0.046440
size=7
pos=37130
flags=K_
[/PACKET]
```

