## ffmpeg（强大的媒体文件转换工具）

//各种视频格式测试用文件  http://samples.mplayerhq.hu/

### 1.通用参数

```
-f fmt			指定格式
-i filename		 指定输入文件名（在Linux下指定0.0(屏幕录制)或摄像头）
-y				覆盖已有文件
-t duration		 指定时长
-fs limit_size	 设置文件大小的上限
-ss time_off	 从指定时间开始(秒)，也支持[-]hh:mm:ss[.xxx]格式
-re 			代表按照帧率发送，尤其是在作为推流工具的时候一定要加入该参数，否则ffmpeg会按照最高速率翔流媒体服务器不停的发送数据
-map			指定输出文件的流映射关系。例如："-map 1:0-map 1:1" 要求将第二个输入文件的第一个流和第二个流写入文件。如果没有设置，会采用默认的映射关系。

```

### 2.视频参数

```
-b					指定比特率(bit/s)，ffmpeg是自动设用VBR的，若制定了该参数则使用平均比特率。
-bitexact			使用标准比特率
-vb					指定视频比特率
-r rate				帧速率(fps)
-s size				指定分辨率
-aspect				设置视频长宽比(4:3,16:9或1.3333,1.7777)
-croptop size		设置顶部切除尺寸(in pixels)
-cropbottom size	设置底部切除尺寸
-cropleft size		设置左切除尺寸
-cropright size		设置右切除尺寸
-padtop size		设置顶部补齐尺寸(in pixels)
-padbottom size		设置底部补齐尺寸(in pixels)
-padleft size		设置左部补齐尺寸(in pixels)
-padright size		设置右部补齐尺寸(in pixels)
-padcolor color		补齐带颜色(000000-FFFFFF)
-vn				   取消视频的输出
-vcodec codec		强制使用codec编解码方式(‘copy’代表不进行重新编码)
```

### 3.音频参数

```
-aq quality				设置音频质量(指定编码)
-ar rate				设置音频采样率（单位HZ）
-ac channes				设置声道数，1单声道，2立体声
-an						取消音频轨
-acodec codec			指定音频编码('copy'代表不做音频转码，直接复制)
-vol volume				设置录制音量大小（默认为256）<百分比>
```

### 4.命令行使用

```
-列出ffmpeg支持的格式
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -formats

File formats:
 D. = Demuxing supported
 .E = Muxing supported
 --
 D  3dostr          3DO STR
  E 3g2             3GP2 (3GPP2 file format)
  E 3gp             3GP (3GPP file format)
 D  4xm             4X Technologies
  E a64             a64 - video for Commodore 64
 D  aa              Audible AA format files
 D  aac             raw ADTS AAC (Advanced Audio Coding)
 D  aax             CRI AAX
 DE ac3             raw AC-3
 D  ace             tri-Ace Audio Container
 D  acm             Interplay ACM
 D  act             ACT Voice file format
 D  adf             Artworx Data Format
 D  adp             ADP
 D  ads             Sony PS2 ADS
  E adts            ADTS AAC (Advanced Audio Coding)
 DE adx             CRI ADX
 D  aea             MD STUDIO audio
 D  afc             AFC
 DE aiff            Audio IFF
 D  aix             CRI AIX
 DE alaw            PCM A-law
 D  alias_pix       Alias/Wavefront PIX image
 DE alp             LEGO Racers ALP
 DE amr             3GPP AMR
 D  amrnb           raw AMR-NB
 D  amrwb           raw AMR-WB
  E amv             AMV
 D  anm             Deluxe Paint Animation
 D  apc             CRYO APC
 D  ape             Monkey's Audio
 DE apm             Ubisoft Rayman 2 APM
 DE apng            Animated Portable Network Graphics
 DE aptx            raw aptX (Audio Processing Technology for Bluetooth)
 DE aptx_hd         raw aptX HD (Audio Processing Technology for Bluetooth)
 D  aqtitle         AQTitle subtitles
 DE argo_asf        Argonaut Games ASF
 D  argo_brp        Argonaut Games BRP
 DE argo_cvg        Argonaut Games CVG
 DE asf             ASF (Advanced / Active Streaming Format)
 D  asf_o           ASF (Advanced / Active Streaming Format)
  E asf_stream      ASF (Advanced / Active Streaming Format)
 DE ass             SSA (SubStation Alpha) subtitle
 DE ast             AST (Audio Stream)
 DE au              Sun AU
 D  av1             AV1 Annex B
 DE avi             AVI (Audio Video Interleaved)
 D  avisynth        AviSynth script
  E avm2            SWF (ShockWave Flash) (AVM2)
 D  avr             AVR (Audio Visual Research)
 D  avs             Argonaut Games Creature Shock
 DE avs2            raw AVS2-P2/IEEE1857.4 video
 D  avs3            raw AVS3-P2/IEEE1857.10
 D  bethsoftvid     Bethesda Softworks VID
 D  bfi             Brute Force & Ignorance
 D  bfstm           BFSTM (Binary Cafe Stream)
 D  bin             Binary text
 D  bink            Bink
 D  binka           Bink Audio
 DE bit             G.729 BIT file format
 D  bmp_pipe        piped bmp sequence
 D  bmv             Discworld II BMV
 D  boa             Black Ops Audio
 D  brender_pix     BRender PIX image
 D  brstm           BRSTM (Binary Revolution Stream)
 D  c93             Interplay C93
 DE caf             Apple CAF (Core Audio Format)
 DE cavsvideo       raw Chinese AVS (Audio Video Standard) video
 D  cdg             CD Graphics
 D  cdxl            Commodore CDXL video
 D  cine            Phantom Cine
 DE codec2          codec2 .c2 muxer
 DE codec2raw       raw codec2 muxer
 D  concat          Virtual concatenation script
  E crc             CRC testing
 D  cri_pipe        piped cri sequence
 DE dash            DASH Muxer
 DE data            raw data
 DE daud            D-Cinema audio
 D  dcstr           Sega DC STR
 D  dds_pipe        piped dds sequence
 D  derf            Xilam DERF
 D  dfa             Chronomaster DFA
 D  dhav            Video DAV
 DE dirac           raw Dirac
 DE dnxhd           raw DNxHD (SMPTE VC-3)
 D  dpx_pipe        piped dpx sequence
 D  dsf             DSD Stream File (DSF)
 D  dshow           DirectShow capture
 D  dsicin          Delphine Software International CIN
 D  dss             Digital Speech Standard (DSS)
 DE dts             raw DTS
 D  dtshd           raw DTS-HD
 DE dv              DV (Digital Video)
 D  dvbsub          raw dvbsub
 D  dvbtxt          dvbtxt
  E dvd             MPEG-2 PS (DVD VOB)
 D  dxa             DXA
 D  ea              Electronic Arts Multimedia
 D  ea_cdata        Electronic Arts cdata
 DE eac3            raw E-AC-3
 D  epaf            Ensoniq Paris Audio File
 D  exr_pipe        piped exr sequence
 DE f32be           PCM 32-bit floating-point big-endian
 DE f32le           PCM 32-bit floating-point little-endian
  E f4v             F4V Adobe Flash Video
 DE f64be           PCM 64-bit floating-point big-endian
 DE f64le           PCM 64-bit floating-point little-endian
 DE ffmetadata      FFmpeg metadata in text
  E fifo            FIFO queue pseudo-muxer
  E fifo_test       Fifo test muxer
 DE film_cpk        Sega FILM / CPK
 DE filmstrip       Adobe Filmstrip
 DE fits            Flexible Image Transport System
 DE flac            raw FLAC
 D  flic            FLI/FLC/FLX animation
 DE flv             FLV (Flash Video)
  E framecrc        framecrc testing
  E framehash       Per-frame hash testing
  E framemd5        Per-frame MD5 testing
 D  frm             Megalux Frame
 D  fsb             FMOD Sample Bank
 D  fwse            Capcom's MT Framework sound
 DE g722            raw G.722
 DE g723_1          raw G.723.1
 DE g726            raw big-endian G.726 ("left-justified")
 DE g726le          raw little-endian G.726 ("right-justified")
 D  g729            G.729 raw format demuxer
 D  gdigrab         GDI API Windows frame grabber
 D  gdv             Gremlin Digital Video
 D  genh            GENeric Header
 DE gif             CompuServe Graphics Interchange Format (GIF)
 D  gif_pipe        piped gif sequence
 DE gsm             raw GSM
 DE gxf             GXF (General eXchange Format)
 DE h261            raw H.261
 DE h263            raw H.263
 DE h264            raw H.264 video
  E hash            Hash testing
 D  hca             CRI HCA
 D  hcom            Macintosh HCOM
  E hds             HDS Muxer
 DE hevc            raw HEVC video
 DE hls             Apple HTTP Live Streaming
 D  hnm             Cryo HNM v4
 DE ico             Microsoft Windows ICO
 D  idcin           id Cinematic
 D  idf             iCE Draw File
 D  iff             IFF (Interchange File Format)
 D  ifv             IFV CCTV DVR
 DE ilbc            iLBC storage
 DE image2          image2 sequence
 DE image2pipe      piped image2 sequence
 D  ingenient       raw Ingenient MJPEG
 D  ipmovie         Interplay MVE
  E ipod            iPod H.264 MP4 (MPEG-4 Part 14)
 D  ipu             raw IPU Video
 DE ircam           Berkeley/IRCAM/CARL Sound Format
  E ismv            ISMV/ISMA (Smooth Streaming)
 D  iss             Funcom ISS
 D  iv8             IndigoVision 8000 video
 DE ivf             On2 IVF
 D  ivr             IVR (Internet Video Recording)
 D  j2k_pipe        piped j2k sequence
 DE jacosub         JACOsub subtitle format
 D  jpeg_pipe       piped jpeg sequence
 D  jpegls_pipe     piped jpegls sequence
 D  jv              Bitmap Brothers JV
 D  kux             KUX (YouKu)
 DE kvag            Simon & Schuster Interactive VAG
  E latm            LOAS/LATM
 D  lavfi           Libavfilter virtual input device
 D  libgme          Game Music Emu demuxer
 D  libopenmpt      Tracker formats (libopenmpt)
 D  live_flv        live RTMP FLV (Flash Video)
 D  lmlm4           raw lmlm4
 D  loas            LOAS AudioSyncStream
 DE lrc             LRC lyrics
 D  luodat          Video CCTV DAT
 D  lvf             LVF
 D  lxf             VR native stream (LXF)
 DE m4v             raw MPEG-4 video
  E matroska        Matroska
 D  matroska,webm   Matroska / WebM
 D  mca             MCA Audio Format
 D  mcc             MacCaption
  E md5             MD5 testing
 D  mgsts           Metal Gear Solid: The Twin Snakes
 DE microdvd        MicroDVD subtitle format
 DE mjpeg           raw MJPEG video
 D  mjpeg_2000      raw MJPEG 2000 video
  E mkvtimestamp_v2 extract pts as timecode v2 format, as defined by mkvtoolnix
 DE mlp             raw MLP
 D  mlv             Magic Lantern Video (MLV)
 D  mm              American Laser Games MM
 DE mmf             Yamaha SMAF
 D  mods            MobiClip MODS
 D  moflex          MobiClip MOFLEX
  E mov             QuickTime / MOV
 D  mov,mp4,m4a,3gp,3g2,mj2 QuickTime / MOV
  E mp2             MP2 (MPEG audio layer 2)
 DE mp3             MP3 (MPEG audio layer 3)
  E mp4             MP4 (MPEG-4 Part 14)
 D  mpc             Musepack
 D  mpc8            Musepack SV8
 DE mpeg            MPEG-1 Systems / MPEG program stream
  E mpeg1video      raw MPEG-1 video
  E mpeg2video      raw MPEG-2 video
 DE mpegts          MPEG-TS (MPEG-2 Transport Stream)
 D  mpegtsraw       raw MPEG-TS (MPEG-2 Transport Stream)
 D  mpegvideo       raw MPEG video
 DE mpjpeg          MIME multipart JPEG
 D  mpl2            MPL2 subtitles
 D  mpsub           MPlayer subtitles
 D  msf             Sony PS3 MSF
 D  msnwctcp        MSN TCP Webcam stream
 D  msp             Microsoft Paint (MSP))
 D  mtaf            Konami PS2 MTAF
 D  mtv             MTV
 DE mulaw           PCM mu-law
 D  musx            Eurocom MUSX
 D  mv              Silicon Graphics Movie
 D  mvi             Motion Pixels MVI
 DE mxf             MXF (Material eXchange Format)
  E mxf_d10         MXF (Material eXchange Format) D-10 Mapping
  E mxf_opatom      MXF (Material eXchange Format) Operational Pattern Atom
 D  mxg             MxPEG clip
 D  nc              NC camera feed
 D  nistsphere      NIST SPeech HEader REsources
 D  nsp             Computerized Speech Lab NSP
 D  nsv             Nullsoft Streaming Video
  E null            raw null video
 DE nut             NUT
 D  nuv             NuppelVideo
 D  obu             AV1 low overhead OBU
  E oga             Ogg Audio
 DE ogg             Ogg
  E ogv             Ogg Video
 DE oma             Sony OpenMG audio
  E opus            Ogg Opus
 D  paf             Amazing Studio Packed Animation File
 D  pam_pipe        piped pam sequence
 D  pbm_pipe        piped pbm sequence
 D  pcx_pipe        piped pcx sequence
 D  pgm_pipe        piped pgm sequence
 D  pgmyuv_pipe     piped pgmyuv sequence
 D  pgx_pipe        piped pgx sequence
 D  photocd_pipe    piped photocd sequence
 D  pictor_pipe     piped pictor sequence
 D  pjs             PJS (Phoenix Japanimation Society) subtitles
 D  pmp             Playstation Portable PMP
 D  png_pipe        piped png sequence
 D  pp_bnk          Pro Pinball Series Soundbank
 D  ppm_pipe        piped ppm sequence
 D  psd_pipe        piped psd sequence
  E psp             PSP MP4 (MPEG-4 Part 14)
 D  psxstr          Sony Playstation STR
 D  pva             TechnoTrend PVA
 D  pvf             PVF (Portable Voice Format)
 D  qcp             QCP
 D  qdraw_pipe      piped qdraw sequence
 D  r3d             REDCODE R3D
 DE rawvideo        raw video
 D  realtext        RealText subtitle format
 D  redspark        RedSpark
 D  rl2             RL2
 DE rm              RealMedia
 DE roq             raw id RoQ
 D  rpl             RPL / ARMovie
 D  rsd             GameCube RSD
 DE rso             Lego Mindstorms RSO
 DE rtp             RTP output
  E rtp_mpegts      RTP/mpegts output format
 DE rtsp            RTSP output
 DE s16be           PCM signed 16-bit big-endian
 DE s16le           PCM signed 16-bit little-endian
 DE s24be           PCM signed 24-bit big-endian
 DE s24le           PCM signed 24-bit little-endian
 DE s32be           PCM signed 32-bit big-endian
 DE s32le           PCM signed 32-bit little-endian
 D  s337m           SMPTE 337M
 DE s8              PCM signed 8-bit
 D  sami            SAMI subtitle format
 DE sap             SAP output
 DE sbc             raw SBC
 D  sbg             SBaGen binaural beats script
 DE scc             Scenarist Closed Captions
  E sdl,sdl2        SDL2 output device
 D  sdp             SDP
 D  sdr2            SDR2
 D  sds             MIDI Sample Dump Standard
 D  sdx             Sample Dump eXchange
  E segment         segment
 D  ser             SER (Simple uncompressed video format for astronomical capturing)
 D  sga             Digital Pictures SGA
 D  sgi_pipe        piped sgi sequence
 D  shn             raw Shorten
 D  siff            Beam Software SIFF
 D  simbiosis_imx   Simbiosis Interactive IMX
 D  sln             Asterisk raw pcm
 DE smjpeg          Loki SDL MJPEG
 D  smk             Smacker
  E smoothstreaming Smooth Streaming Muxer
 D  smush           LucasArts Smush
 D  sol             Sierra SOL
 DE sox             SoX native
 DE spdif           IEC 61937 (used on S/PDIF - IEC958)
  E spx             Ogg Speex
 DE srt             SubRip subtitle
 D  stl             Spruce subtitle format
  E stream_segment,ssegment streaming segment muxer
  E streamhash      Per-stream hash testing
 D  subviewer       SubViewer subtitle format
 D  subviewer1      SubViewer v1 subtitle format
 D  sunrast_pipe    piped sunrast sequence
 DE sup             raw HDMV Presentation Graphic Stream subtitles
 D  svag            Konami PS2 SVAG
  E svcd            MPEG-2 PS (SVCD)
 D  svg_pipe        piped svg sequence
 D  svs             Square SVS
 DE swf             SWF (ShockWave Flash)
 D  tak             raw TAK
 D  tedcaptions     TED Talks captions
  E tee             Multiple muxer tee
 D  thp             THP
 D  tiertexseq      Tiertex Limited SEQ
 D  tiff_pipe       piped tiff sequence
 D  tmv             8088flex TMV
 DE truehd          raw TrueHD
 DE tta             TTA (True Audio)
  E ttml            TTML subtitle
 D  tty             Tele-typewriter
 D  txd             Renderware TeXture Dictionary
 D  ty              TiVo TY Stream
 DE u16be           PCM unsigned 16-bit big-endian
 DE u16le           PCM unsigned 16-bit little-endian
 DE u24be           PCM unsigned 24-bit big-endian
 DE u24le           PCM unsigned 24-bit little-endian
 DE u32be           PCM unsigned 32-bit big-endian
 DE u32le           PCM unsigned 32-bit little-endian
 DE u8              PCM unsigned 8-bit
  E uncodedframecrc uncoded framecrc testing
 D  v210            Uncompressed 4:2:2 10-bit
 D  v210x           Uncompressed 4:2:2 10-bit
 D  vag             Sony PS2 VAG
 DE vc1             raw VC-1 video
 DE vc1test         VC-1 test bitstream
  E vcd             MPEG-1 Systems / MPEG program stream (VCD)
 D  vfwcap          VfW video capture
 DE vidc            PCM Archimedes VIDC
 D  vividas         Vividas VIV
 D  vivo            Vivo
 D  vmd             Sierra VMD
  E vob             MPEG-2 PS (VOB)
 D  vobsub          VobSub subtitle format
 DE voc             Creative Voice
 D  vpk             Sony PS2 VPK
 D  vplayer         VPlayer subtitles
 D  vqf             Nippon Telegraph and Telephone Corporation (NTT) TwinVQ
 DE w64             Sony Wave64
 DE wav             WAV / WAVE (Waveform Audio)
 D  wc3movie        Wing Commander III movie
  E webm            WebM
  E webm_chunk      WebM Chunk Muxer
 DE webm_dash_manifest WebM DASH Manifest
  E webp            WebP
 D  webp_pipe       piped webp sequence
 DE webvtt          WebVTT subtitle
 DE wsaud           Westwood Studios audio
 D  wsd             Wideband Single-bit Data (WSD)
 D  wsvqa           Westwood Studios VQA
 DE wtv             Windows Television (WTV)
 DE wv              raw WavPack
 D  wve             Psion 3 audio
 D  xa              Maxis XA
 D  xbin            eXtended BINary text (XBIN)
 D  xbm_pipe        piped xbm sequence
 D  xmv             Microsoft XMV
 D  xpm_pipe        piped xpm sequence
 D  xvag            Sony PS3 XVAG
 D  xwd_pipe        piped xwd sequence
 D  xwma            Microsoft xWMA
 D  yop             Psygnosis YOP
 DE yuv4mpegpipe    YUV4MPEG pipe


Z:\9_ffmpeg\ffmpeg\bin\ffmpeg Z:\9_ffmpeg\videos\big_buck_bunny.mp4
```

```
-裁剪一段媒体文件（音频/视频）
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -ss 00:00:50.0 -codec copy -t 10 Z:\9_ffmpeg\videos\output_cut.mp4

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
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_cut.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: h264 (Constrained Baseline) (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709), 640x360, q=2-31, 612 kb/s, 23.96 fps, 24 tbr, 19200 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
  Stream #0:1(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 65 kb/s (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Sound Media Handler
      vendor_id       : [0][0][0][0]
Stream mapping:
  Stream #0:1 -> #0:0 (copy)
  Stream #0:0 -> #0:1 (copy)
Press [q] to stop, [?] for help
frame=  224 fps=9.5 q=-1.0 Lsize=     932kB time=00:00:10.00 bitrate= 763.6kbits/s speed=0.424x
video:850kB audio:77kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.567556%
```

```
-将视频切割为多个视频
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -t 00:00:30 -c copy Z:\9_ffmpeg\videos\output_small-1.mp4 -ss 00:00:30 -codec copy Z:\9_ffmpeg\videos\output_small-2.mp4

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
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_small-1.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: h264 (Constrained Baseline) (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709), 640x360, q=2-31, 612 kb/s, 23.96 fps, 24 tbr, 19200 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
  Stream #0:1(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 65 kb/s (default)
Output #1, mp4, to 'Z:\9_ffmpeg\videos\output_small-2.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #1:0(eng): Video: h264 (Constrained Baseline) (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709), 640x360, q=2-31, 612 kb/s, 23.96 fps, 24 tbr, 19200 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
  Stream #1:1(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 65 kb/s (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Sound Media Handler
      vendor_id       : [0][0][0][0]
Stream mapping:
  Stream #0:1 -> #0:0 (copy)
  Stream #0:0 -> #0:1 (copy)
  Stream #0:1 -> #1:0 (copy)
  Stream #0:0 -> #1:1 (copy)
Press [q] to stop, [?] for help
frame=  720 fps= 27 q=-1.0 Lq=-1.0 size=    2333kB time=00:00:30.13 bitrate= 634.1kbits/s speed=1.14x
video:4375kB audio:478kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: unknown
```

```
-提取视频中的音频
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -vn -acodec copy Z:\9_ffmpeg\videos\output_only_audio.m4a

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
Output #0, ipod, to 'Z:\9_ffmpeg\videos\output_only_audio.m4a':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 65 kb/s (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Sound Media Handler
      vendor_id       : [0][0][0][0]
Stream mapping:
  Stream #0:0 -> #0:0 (copy)
Press [q] to stop, [?] for help
size=     484kB time=00:01:00.13 bitrate=  65.9kbits/s speed=6.98x
video:0kB audio:478kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 1.225672%
```

```
-使视频静音
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -an -vcodec copy Z:\9_ffmpeg\videos\output_only_video.mp4

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
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_only_video.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: h264 (Constrained Baseline) (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709), 640x360, q=2-31, 612 kb/s, 23.96 fps, 24 tbr, 19200 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
Stream mapping:
  Stream #0:1 -> #0:0 (copy)
Press [q] to stop, [?] for help
frame= 1440 fps=113 q=-1.0 Lsize=    4497kB time=00:00:59.95 bitrate= 614.5kbits/s speed=4.71x
video:4491kB audio:0kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.147523%
```

```
-从MP4文件中冲去视频流导出为裸H264数据
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -an -vcodec copy -bsf:v h264_mp4toannexb Z:\9_ffmpeg\videos\output_only_h264.h264

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
Output #0, h264, to 'Z:\9_ffmpeg\videos\output_only_h264.h264':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: h264 (Constrained Baseline) (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709), 640x360, q=2-31, 612 kb/s, 23.96 fps, 24 tbr, 600 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
Stream mapping:
  Stream #0:1 -> #0:0 (copy)
Press [q] to stop, [?] for help
frame= 1440 fps=104 q=-1.0 Lsize=    4485kB time=00:00:59.96 bitrate= 612.7kbits/s speed=4.34x
video:4485kB audio:0kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.000000%
```

```
-使用AAC音频数据和H264的视频生成MP4文件
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\output_only_audio.aac -i Z:\9_ffmpeg\videos\output_only_h264.h264 -acodec copy -bsf:a aac_adtstoasc -vcodec copy -f mp4 Z:\9_ffmpeg\videos\output_audioAndH264Data_to_mix_newmp4.mp4

Input #0, mov,mp4,m4a,3gp,3g2,mj2, from 'Z:\9_ffmpeg\videos\output_only_audio.aac':
  Metadata:
    major_brand     : M4A
    minor_version   : 512
    compatible_brands: M4A isomiso2
    encoder         : Lavf59.3.100
  Duration: 00:01:00.10, start: 0.000000, bitrate: 65 kb/s
  Stream #0:0(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 65 kb/s (default)
    Metadata:
      handler_name    : Apple Sound Media Handler
      vendor_id       : [0][0][0][0]
[h264 @ 000001955b624100] non-existing SPS 0 referenced in buffering period
    Last message repeated 1 times
Input #1, h264, from 'Z:\9_ffmpeg\videos\output_only_h264.h264':
  Duration: N/A, bitrate: N/A
  Stream #1:0: Video: h264 (Constrained Baseline), yuv420p(tv, smpte170m/smpte170m/bt709, progressive), 640x360, 25 fps, 25 tbr, 1200k tbn
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_audioAndH264Data_to_mix_newmp4.mp4':
  Metadata:
    major_brand     : M4A
    minor_version   : 512
    compatible_brands: M4A isomiso2
    encoder         : Lavf59.3.100
  Stream #0:0: Video: h264 (Constrained Baseline) (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709, progressive), 640x360, q=2-31, 25 fps, 25 tbr, 1200k tbn
  Stream #0:1(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 65 kb/s (default)
    Metadata:
      handler_name    : Apple Sound Media Handler
      vendor_id       : [0][0][0][0]
Stream mapping:
  Stream #1:0 -> #0:0 (copy)
  Stream #0:0 -> #0:1 (copy)
Press [q] to stop, [?] for help
[mp4 @ 000001955b6358c0] Timestamps are unset in a packet for stream 0. This is deprecated and will stop working in the future. Fix your code to set the timestamps properly
frame= 1440 fps=356 q=-1.0 Lsize=    4996kB time=00:01:00.13 bitrate= 680.5kbits/s speed=14.9x
video:4485kB audio:478kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.676335%
```

```
-wav转aac
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\test.wav -acodec libfdk_aac Z:\9_ffmpeg\videos\output_wav2aac.aac

Input #0, wav, from 'Z:\9_ffmpeg\videos\test.wav':
  Duration: 00:00:00.57, bitrate: 1411 kb/s
  Stream #0:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 44100 Hz, stereo, s16, 1411 kb/s
Unknown encoder 'libfdk_aac'
使用的这个版本是别人编译好的windows版，没有集成这个库(https://www.gyan.dev/ffmpeg/builds/)
如果需要支持，则需要手动编译 ffmpeg
```

```
-wav导出PCM数据
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\test.wav -acodec pcm_s16le -f s16le Z:\9_ffmpeg\videos\output_wav2pcm.pcm

Guessed Channel Layout for Input Stream #0.0 : stereo
Input #0, wav, from 'Z:\9_ffmpeg\videos\test.wav':
  Duration: 00:00:00.57, bitrate: 1411 kb/s
  Stream #0:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 44100 Hz, stereo, s16, 1411 kb/s
File 'Z:\9_ffmpeg\videos\output_wav2pcm.pcm' already exists. Overwrite? [y/N] y
Stream mapping:
  Stream #0:0 -> #0:0 (pcm_s16le (native) -> pcm_s16le (native))
Press [q] to stop, [?] for help
Output #0, s16le, to 'Z:\9_ffmpeg\videos\output_wav2pcm.pcm':
  Metadata:
    encoder         : Lavf59.3.100
  Stream #0:0: Audio: pcm_s16le, 44100 Hz, stereo, s16, 1411 kb/s
    Metadata:
      encoder         : Lavc59.1.101 pcm_s16le
size=      98kB time=00:00:00.56 bitrate=1411.2kbits/s speed=0.00282x

Z:\9_ffmpeg\ffmpeg\bin\ffplay Z:\9_ffmpeg\videos\output_wav2pcm.pcm -f s16le -channels 2 -ar 44100
```

```
-重新编码视频文件，复制音频流，同时封装到MP4格式的文件中
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\testflv.flv -vcodec libx264 -acodec copy Z:\9_ffmpeg\videos\output_reformatflv2mp4.mp4

Input #0, flv, from 'Z:\9_ffmpeg\videos\testflv.flv':
  Metadata:
    encoder         : Lavf54.6.100
  Duration: 00:02:04.76, start: 0.080000, bitrate: 1803 kb/s
  Stream #0:0: Video: h264 (Main), yuv420p(progressive), 800x450 [SAR 1:1 DAR 16:9], 1600 kb/s, 25 fps, 25 tbr, 1k tbn
  Stream #0:1: Audio: aac (LC), 44100 Hz, stereo, fltp, 96 kb/s
Stream mapping:
  Stream #0:0 -> #0:0 (h264 (native) -> h264 (libx264))
  Stream #0:1 -> #0:1 (copy)
Press [q] to stop, [?] for help
[libx264 @ 000002d011015100] using SAR=1/1
[libx264 @ 000002d011015100] using cpu capabilities: MMX2 SSE2Fast SSSE3 SSE4.2 AVX FMA3 BMI2 AVX2
[libx264 @ 000002d011015100] profile High, level 3.0, 4:2:0, 8-bit
[libx264 @ 000002d011015100] 264 - core 163 r3059 b684ebe - H.264/MPEG-4 AVC codec - Copyleft 2003-2021 - http://www.videolan.org/x264.html - options: cabac=1 ref=3 deblock=1:0:0 analyse=0x3:0x113 me=hex subme=7 psy=1 psy_rd=1.00:0.00 mixed_ref=1 me_range=16 chroma_me=1 trellis=1 8x8dct=1 cqm=0 deadzone=21,11 fast_pskip=1 chroma_qp_offset=-2 threads=12 lookahead_threads=2 sliced_threads=0 nr=0 decimate=1 interlaced=0 bluray_compat=0 constrained_intra=0 bframes=3 b_pyramid=2 b_adapt=1 b_bias=0 direct=1 weightb=1 open_gop=0 weightp=2 keyint=250 keyint_min=25 scenecut=40 intra_refresh=0 rc_lookahead=40 rc=crf mbtree=1 crf=23.0 qcomp=0.60 qpmin=0 qpmax=69 qpstep=4 ip_ratio=1.40 aq=1:1.00
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_reformatflv2mp4.mp4':
  Metadata:
    encoder         : Lavf59.3.100
  Stream #0:0: Video: h264 (avc1 / 0x31637661), yuv420p(progressive), 800x450 [SAR 1:1 DAR 16:9], q=2-31, 25 fps, 12800 tbn
    Metadata:
      encoder         : Lavc59.1.101 libx264
    Side data:
      cpb: bitrate max/min/avg: 0/0/0 buffer size: 0 vbv_delay: N/A
  Stream #0:1: Audio: aac (LC) (mp4a / 0x6134706D), 44100 Hz, stereo, fltp, 96 kb/s
frame= 3117 fps=100 q=-1.0 Lsize=   11954kB time=00:02:04.64 bitrate= 785.7kbits/s dup=130 drop=0 speed=4.01x
video:10361kB audio:1459kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 1.136663%
[libx264 @ 000002d011015100] frame I:45    Avg QP:15.49  size: 23094
[libx264 @ 000002d011015100] frame P:997   Avg QP:21.22  size:  6623
[libx264 @ 000002d011015100] frame B:2075  Avg QP:22.82  size:  1430
[libx264 @ 000002d011015100] consecutive B-frames:  5.9% 10.5% 16.5% 67.1%
[libx264 @ 000002d011015100] mb I  I16..4: 42.3% 31.9% 25.8%
[libx264 @ 000002d011015100] mb P  I16..4:  5.0%  7.6%  1.6%  P16..4: 25.2%  9.1%  5.0%  0.0%  0.0%    skip:46.4%
[libx264 @ 000002d011015100] mb B  I16..4:  0.6%  0.7%  0.2%  B16..8: 20.4%  2.9%  0.5%  direct: 2.0%  skip:72.8%  L0:42.6% L1:49.9% BI: 7.5%
[libx264 @ 000002d011015100] 8x8 transform intra:48.1% inter:66.0%
[libx264 @ 000002d011015100] coded y,uvDC,uvAC intra: 35.5% 58.4% 32.0% inter: 7.4% 13.5% 2.5%
[libx264 @ 000002d011015100] i16 v,h,dc,p: 47% 31%  9% 13%
[libx264 @ 000002d011015100] i8 v,h,dc,ddl,ddr,vr,hd,vl,hu: 20% 22% 32%  3%  4%  4%  4%  4%  6%
[libx264 @ 000002d011015100] i4 v,h,dc,ddl,ddr,vr,hd,vl,hu: 22% 25% 16%  6%  7%  7%  7%  5%  6%
[libx264 @ 000002d011015100] i8c dc,h,v,p: 53% 25% 15%  6%
[libx264 @ 000002d011015100] Weighted P-Frames: Y:17.0% UV:16.1%
[libx264 @ 000002d011015100] ref P L0: 65.8% 14.5% 14.6%  4.8%  0.3%
[libx264 @ 000002d011015100] ref B L0: 87.6% 10.2%  2.1%
[libx264 @ 000002d011015100] ref B L1: 96.4%  3.6%
[libx264 @ 000002d011015100] kb/s:680.74
```

```
-MP4视频转gif
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -vf scale=100:-1 -t 5 -r 10 Z:\9_ffmpeg\videos\output_mp4togif.gif
宽高比不懂，宽度改为100（使用VideoFilter的scaleFilter）,帧率改为10 ，只处理前5秒的视频，生成gif

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
Stream mapping:
  Stream #0:1 -> #0:0 (h264 (native) -> gif (native))
Press [q] to stop, [?] for help
Output #0, gif, to 'Z:\9_ffmpeg\videos\output_mp4togif.gif':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: gif, bgr8(pc, smpte170m/smpte170m/bt709, progressive), 100x56, q=2-31, 200 kb/s, 10 fps, 100 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
      encoder         : Lavc59.1.101 gif
frame=   50 fps= 10 q=-0.0 Lsize=      55kB time=00:00:04.91 bitrate=  91.4kbits/s dup=0 drop=68 speed=1.01x
video:55kB audio:0kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.035674%
```

```
-视频画面生成图片
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -r 0.25 Z:\9_ffmpeg\videos\output_image\output_video2pic_frames_%04d.png
每四秒截取一帧生成一张图片生成的图片从output_video2pic_frames_0001.png开始递增

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
Stream mapping:
  Stream #0:1 -> #0:0 (h264 (native) -> png (native))
Press [q] to stop, [?] for help
Output #0, image2, to 'Z:\9_ffmpeg\videos\output_image\output_video2pic_frames_%04d.png':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: png, rgb24(pc, smpte170m/smpte170m/bt709, progressive), 640x360, q=2-31, 200 kb/s, 0.25 fps, 0.25 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
      encoder         : Lavc59.1.101 png
frame=   17 fps=0.7 q=-0.0 Lsize=N/A time=00:01:08.00 bitrate=N/A dup=0 drop=1423 speed=2.88x
video:6627kB audio:0kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: unknown

```

```
-使用一组图片生成gif
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\output_image\output_video2pic_frames_%04d.png -r 5 Z:\9_ffmpeg\videos\output_pic2Gif.gif

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
  Stream #0:2(eng): Data: none (rtp  / 0x20707472), 45 kb/s
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : hint media handler
  Stream #0:3(eng): Data: none (rtp  / 0x20707472), 5 kb/s
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : hint media handler
Stream mapping:
  Stream #0:1 -> #0:0 (h264 (native) -> png (native))
Press [q] to stop, [?] for help
Output #0, image2, to 'Z:\9_ffmpeg\videos\output_image\output_video2pic_frames_%04d.png':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: png, rgb24(pc, smpte170m/smpte170m/bt709, progressive), 640x360, q=2-31, 200 kb/s, 0.25 fps, 0.25 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
      encoder         : Lavc59.1.101 png
frame=   17 fps=0.7 q=-0.0 Lsize=N/A time=00:01:08.00 bitrate=N/A dup=0 drop=1423 speed=2.88x
video:6627kB audio:0kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: unknown
PS C:\Users\14501> Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\output_image\output_video2pic_frames_%04d.png -r 5 Z:\9_ffmpeg\videos\output_pic2Gif.gif
ffmpeg version 2021-06-13-git-3ce272a9da-essentials_build-www.gyan.dev Copyright (c) 2000-2021 the FFmpeg developers
  built with gcc 10.3.0 (Rev2, Built by MSYS2 project)
  configuration: --enable-gpl --enable-version3 --enable-static --disable-w32threads --disable-autodetect --enable-fontconfig --enable-iconv --enable-gnutls --enable-libxml2 --enable-gmp --enable-lzma --enable-zlib --enable-libsrt --enable-libssh --enable-libzmq --enable-avisynth --enable-sdl2 --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxvid --enable-libaom --enable-libopenjpeg --enable-libvpx --enable-libass --enable-libfreetype --enable-libfribidi --enable-libvidstab --enable-libvmaf --enable-libzimg --enable-amf --enable-cuda-llvm --enable-cuvid --enable-ffnvcodec --enable-nvdec --enable-nvenc --enable-d3d11va --enable-dxva2 --enable-libmfx --enable-libgme --enable-libopenmpt --enable-libopencore-amrwb --enable-libmp3lame --enable-libtheora --enable-libvo-amrwbenc --enable-libgsm --enable-libopencore-amrnb --enable-libopus --enable-libspeex --enable-libvorbis --enable-librubberband
  libavutil      57.  0.100 / 57.  0.100
  libavcodec     59.  1.101 / 59.  1.101
  libavformat    59.  3.100 / 59.  3.100
  libavdevice    59.  0.100 / 59.  0.100
  libavfilter     8.  0.102 /  8.  0.102
  libswscale      6.  0.100 /  6.  0.100
  libswresample   4.  0.100 /  4.  0.100
  libpostproc    56.  0.100 / 56.  0.100
Input #0, image2, from 'Z:\9_ffmpeg\videos\output_image\output_video2pic_frames_%04d.png':
  Duration: 00:00:00.68, start: 0.000000, bitrate: N/A
  Stream #0:0: Video: png, rgb24(pc), 640x360, 25 fps, 25 tbr, 25 tbn
Stream mapping:
  Stream #0:0 -> #0:0 (png (native) -> gif (native))
Press [q] to stop, [?] for help
Output #0, gif, to 'Z:\9_ffmpeg\videos\output_pic2Gif.gif':
  Metadata:
    encoder         : Lavf59.3.100
  Stream #0:0: Video: gif, bgr8(pc, progressive), 640x360, q=2-31, 200 kb/s, 5 fps, 100 tbn
    Metadata:
      encoder         : Lavc59.1.101 gif
frame=    5 fps=0.6 q=-0.0 Lsize=     417kB time=00:00:00.81 bitrate=4215.1kbits/s dup=0 drop=12 speed=0.0995x
video:417kB audio:0kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.004686%
```

```
-使用音量效果器
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\test.wav -af 'volume=0.5' Z:\9_ffmpeg\videos\output_wav_vol2half.wav

Guessed Channel Layout for Input Stream #0.0 : stereo
Input #0, wav, from 'Z:\9_ffmpeg\videos\test.wav':
  Duration: 00:00:00.57, bitrate: 1411 kb/s
  Stream #0:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 44100 Hz, stereo, s16, 1411 kb/s
Stream mapping:
  Stream #0:0 -> #0:0 (pcm_s16le (native) -> pcm_s16le (native))
Press [q] to stop, [?] for help
Output #0, wav, to 'Z:\9_ffmpeg\videos\output_wav_vol2half.wav':
  Metadata:
    ISFT            : Lavf59.3.100
  Stream #0:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 44100 Hz, stereo, s16, 1411 kb/s
    Metadata:
      encoder         : Lavc59.1.101 pcm_s16le
size=      98kB time=00:00:00.56 bitrate=1412.3kbits/s speed=0.404x
video:0kB audio:98kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.078000%

没有听出来。。
```

```
-淡入效果
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\testwav.wav -filter_complex afade=t=in:ss=0:d=5 Z:\9_ffmpeg\videos\output_fadein.wav
前五秒处理成淡入的效果

Guessed Channel Layout for Input Stream #0.0 : stereo
Input #0, wav, from 'Z:\9_ffmpeg\videos\testwav.wav':
  Metadata:
    encoder         : Lavf58.29.100
  Duration: 00:01:00.14, bitrate: 705 kb/s
  Stream #0:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 22050 Hz, stereo, s16, 705 kb/s
Stream mapping:
  Stream #0:0 (pcm_s16le) -> afade
  afade -> Stream #0:0 (pcm_s16le)
Press [q] to stop, [?] for help
Output #0, wav, to 'Z:\9_ffmpeg\videos\output_fadein.wav':
  Metadata:
    ISFT            : Lavf59.3.100
  Stream #0:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 22050 Hz, stereo, s16, 705 kb/s
    Metadata:
      encoder         : Lavc59.1.101 pcm_s16le
size=    5180kB time=00:01:00.13 bitrate= 705.6kbits/s speed=0.832x
video:0kB audio:5180kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.001470%
```

```
-淡出效果
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\testwav.wav -filter_complex afade=t=out:st=55:d=5 Z:\9_ffmpeg\videos\output_fadeout.wav
200秒开始的五秒钟处理成淡出的效果

Guessed Channel Layout for Input Stream #0.0 : stereo
Input #0, wav, from 'Z:\9_ffmpeg\videos\testwav.wav':
  Metadata:
    encoder         : Lavf58.29.100
  Duration: 00:01:00.14, bitrate: 705 kb/s
  Stream #0:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 22050 Hz, stereo, s16, 705 kb/s
Stream mapping:
  Stream #0:0 (pcm_s16le) -> afade
  afade -> Stream #0:0 (pcm_s16le)
Press [q] to stop, [?] for help
Output #0, wav, to 'Z:\9_ffmpeg\videos\output_fadeout.wav':
  Metadata:
    ISFT            : Lavf59.3.100
  Stream #0:0: Audio: pcm_s16le ([1][0][0][0] / 0x0001), 22050 Hz, stereo, s16, 705 kb/s
    Metadata:
      encoder         : Lavc59.1.101 pcm_s16le
size=    5180kB time=00:01:00.13 bitrate= 705.6kbits/s speed=0.413x
video:0kB audio:5180kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.001470%
```

```
-两路声音合并
ffmpeg -i vocal.wav -i accompany.wav -filter_complex amix=inputs=2:duration=shortest output.wav
将这两个文件进行mix，按照时间长度短的最为最终输出的output.wav的时间长度
```

```
-声音变速不变调
ffmpeg -i vocal.wav -filter_complex atempo=0.5 output.wav
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\testwav.wav -filter_complex atempo=0.5 Z:\9_ffmpeg\videos\output_slowspeed.wav
0.5倍速度生成output.wav，时间长度会变成2倍。音高不变（变速不变调）
```

```
-视频加水印
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -i Z:\9_ffmpeg\videos\earth.png -filter_complex '[0:v][1:v]overlay[out]' -map '[out]' Z:\9_ffmpeg\videos\output_overlay.mp4

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
Input #1, png_pipe, from 'Z:\9_ffmpeg\videos\earth.png':
  Duration: N/A, bitrate: N/A
  Stream #1:0: Video: png, rgba(pc), 98x99 [SAR 3779:3779 DAR 98:99], 25 fps, 25 tbr, 25 tbn
Stream mapping:
  Stream #0:1 (h264) -> overlay:main
  Stream #1:0 (png) -> overlay:overlay
  overlay -> Stream #0:0 (libx264)
Press [q] to stop, [?] for help
[libx264 @ 00000238b0a84e00] using cpu capabilities: MMX2 SSE2Fast SSSE3 SSE4.2 AVX FMA3 BMI2 AVX2
[libx264 @ 00000238b0a84e00] profile High, level 3.0, 4:2:0, 8-bit
[libx264 @ 00000238b0a84e00] 264 - core 163 r3059 b684ebe - H.264/MPEG-4 AVC codec - Copyleft 2003-2021 - http://www.videolan.org/x264.html - options: cabac=1 ref=3 deblock=1:0:0 analyse=0x3:0x113 me=hex subme=7 psy=1 psy_rd=1.00:0.00 mixed_ref=1 me_range=16 chroma_me=1 trellis=1 8x8dct=1 cqm=0 deadzone=21,11 fast_pskip=1 chroma_qp_offset=-2 threads=11 lookahead_threads=1 sliced_threads=0 nr=0 decimate=1 interlaced=0 bluray_compat=0 constrained_intra=0 bframes=3 b_pyramid=2 b_adapt=1 b_bias=0 direct=1 weightb=1 open_gop=0 weightp=2 keyint=250 keyint_min=24 scenecut=40 intra_refresh=0 rc_lookahead=40 rc=crf mbtree=1 crf=23.0 qcomp=0.60 qpmin=0 qpmax=69 qpstep=4 ip_ratio=1.40 aq=1:1.00
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_overlay.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0: Video: h264 (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709, progressive), 640x360, q=2-31, 24 fps, 12288 tbn (default)
    Metadata:
      encoder         : Lavc59.1.101 libx264
    Side data:
      cpb: bitrate max/min/avg: 0/0/0 buffer size: 0 vbv_delay: N/A
frame= 1440 fps=122 q=-1.0 Lsize=    4177kB time=00:00:59.87 bitrate= 571.5kbits/s speed=5.09x
video:4164kB audio:0kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.329639%

问题：为甚没有声音？？？
```

```
-视频提亮
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\testflv.flv -c:v libx264 -b:v1600k -vf eq=brightness=0.25 -f mp4 Z:\9_ffmpeg\videos\output_video_bright_change.mp4
brightness取值 -1.0~1.0,默认是0
-c:a libfdk_aac 

问题：eq=brightness=0.25 invalid argument
```

```
-视频增加对比度
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\testflv.flv -c:v libx264 -b:v 1600k -vf eq=contrast=1.5 -f mp4 Z:\9_ffmpeg\videos\output_video_contrast.mp4
contrast取值 -2.0~2.0,默认是1.0

Input #0, flv, from 'Z:\9_ffmpeg\videos\testflv.flv':
  Metadata:
    encoder         : Lavf54.6.100
  Duration: 00:02:04.76, start: 0.080000, bitrate: 1803 kb/s
  Stream #0:0: Video: h264 (Main), yuv420p(progressive), 800x450 [SAR 1:1 DAR 16:9], 1600 kb/s, 25 fps, 25 tbr, 1k tbn
  Stream #0:1: Audio: aac (LC), 44100 Hz, stereo, fltp, 96 kb/s
Stream mapping:
  Stream #0:0 -> #0:0 (h264 (native) -> h264 (libx264))
  Stream #0:1 -> #0:1 (aac (native) -> aac (native))
Press [q] to stop, [?] for help
[libx264 @ 000001c16cfff700] using SAR=1/1
[libx264 @ 000001c16cfff700] using cpu capabilities: MMX2 SSE2Fast SSSE3 SSE4.2 AVX FMA3 BMI2 AVX2
[libx264 @ 000001c16cfff700] profile High, level 3.0, 4:2:0, 8-bit
[libx264 @ 000001c16cfff700] 264 - core 163 r3059 b684ebe - H.264/MPEG-4 AVC codec - Copyleft 2003-2021 - http://www.videolan.org/x264.html - options: cabac=1 ref=3 deblock=1:0:0 analyse=0x3:0x113 me=hex subme=7 psy=1 psy_rd=1.00:0.00 mixed_ref=1 me_range=16 chroma_me=1 trellis=1 8x8dct=1 cqm=0 deadzone=21,11 fast_pskip=1 chroma_qp_offset=-2 threads=12 lookahead_threads=2 sliced_threads=0 nr=0 decimate=1 interlaced=0 bluray_compat=0 constrained_intra=0 bframes=3 b_pyramid=2 b_adapt=1 b_bias=0 direct=1 weightb=1 open_gop=0 weightp=2 keyint=250 keyint_min=25 scenecut=40 intra_refresh=0 rc_lookahead=40 rc=abr mbtree=1 bitrate=1600 ratetol=1.0 qcomp=0.60 qpmin=0 qpmax=69 qpstep=4 ip_ratio=1.40 aq=1:1.00
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_video_contrast.mp4':
  Metadata:
    encoder         : Lavf59.3.100
  Stream #0:0: Video: h264 (avc1 / 0x31637661), yuv420p(progressive), 800x450 [SAR 1:1 DAR 16:9], q=2-31, 1600 kb/s, 25 fps, 12800 tbn
    Metadata:
      encoder         : Lavc59.1.101 libx264
    Side data:
      cpb: bitrate max/min/avg: 0/0/1600000 buffer size: 0 vbv_delay: N/A
  Stream #0:1: Audio: aac (LC) (mp4a / 0x6134706D), 44100 Hz, stereo, fltp, 128 kb/s
    Metadata:
      encoder         : Lavc59.1.101 aac
frame= 3117 fps= 79 q=-1.0 Lsize=   27771kB time=00:02:04.64 bitrate=1825.2kbits/s dup=130 drop=0 speed=3.16x
video:25705kB audio:1964kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.369434%
[libx264 @ 000001c16cfff700] frame I:39    Avg QP: 8.94  size: 39610
[libx264 @ 000001c16cfff700] frame P:1018  Avg QP:14.75  size: 16108
[libx264 @ 000001c16cfff700] frame B:2060  Avg QP:17.18  size:  4067
[libx264 @ 000001c16cfff700] consecutive B-frames:  7.4% 10.4%  9.0% 73.1%
[libx264 @ 000001c16cfff700] mb I  I16..4: 55.6%  5.8% 38.6%
[libx264 @ 000001c16cfff700] mb P  I16..4: 11.1%  4.5%  4.0%  P16..4: 21.3% 11.4%  7.5%  0.0%  0.0%    skip:40.2%
[libx264 @ 000001c16cfff700] mb B  I16..4:  0.3%  0.4%  0.7%  B16..8: 16.5%  5.0%  1.5%  direct:12.7%  skip:62.9%  L0:42.5% L1:42.6% BI:14.8%
[libx264 @ 000001c16cfff700] final ratefactor: 17.39
[libx264 @ 000001c16cfff700] 8x8 transform intra:20.9% inter:41.2%
[libx264 @ 000001c16cfff700] coded y,uvDC,uvAC intra: 35.6% 80.3% 69.6% inter: 12.6% 25.0% 9.2%
[libx264 @ 000001c16cfff700] i16 v,h,dc,p: 87%  8%  3%  3%
[libx264 @ 000001c16cfff700] i8 v,h,dc,ddl,ddr,vr,hd,vl,hu: 19% 22% 28%  4%  5%  5%  5%  5%  7%
[libx264 @ 000001c16cfff700] i4 v,h,dc,ddl,ddr,vr,hd,vl,hu: 23% 25% 15%  5%  7%  7%  7%  6%  7%
[libx264 @ 000001c16cfff700] i8c dc,h,v,p: 46% 28% 18%  8%
[libx264 @ 000001c16cfff700] Weighted P-Frames: Y:18.2% UV:16.4%
[libx264 @ 000001c16cfff700] ref P L0: 60.3% 21.3% 13.2%  4.8%  0.4%
[libx264 @ 000001c16cfff700] ref B L0: 85.6% 12.3%  2.2%
[libx264 @ 000001c16cfff700] ref B L1: 95.8%  4.2%
[libx264 @ 000001c16cfff700] kb/s:1688.90
[aac @ 000001c16cac9bc0] Qavg: 1692.098

```

```
-视频旋转效果器
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4   -b:v 1600k -vf "transpose=1"  Z:\9_ffmpeg\videos\output_video_transpose.mp4

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
Stream mapping:
  Stream #0:1 -> #0:0 (h264 (native) -> h264 (libx264))
  Stream #0:0 -> #0:1 (aac (native) -> aac (native))
Press [q] to stop, [?] for help
[libx264 @ 000001f530e50340] using cpu capabilities: MMX2 SSE2Fast SSSE3 SSE4.2 AVX FMA3 BMI2 AVX2
[libx264 @ 000001f530e50340] profile High, level 3.0, 4:2:0, 8-bit
[libx264 @ 000001f530e50340] 264 - core 163 r3059 b684ebe - H.264/MPEG-4 AVC codec - Copyleft 2003-2021 - http://www.videolan.org/x264.html - options: cabac=1 ref=3 deblock=1:0:0 analyse=0x3:0x113 me=hex subme=7 psy=1 psy_rd=1.00:0.00 mixed_ref=1 me_range=16 chroma_me=1 trellis=1 8x8dct=1 cqm=0 deadzone=21,11 fast_pskip=1 chroma_qp_offset=-2 threads=12 lookahead_threads=2 sliced_threads=0 nr=0 decimate=1 interlaced=0 bluray_compat=0 constrained_intra=0 bframes=3 b_pyramid=2 b_adapt=1 b_bias=0 direct=1 weightb=1 open_gop=0 weightp=2 keyint=250 keyint_min=24 scenecut=40 intra_refresh=0 rc_lookahead=40 rc=abr mbtree=1 bitrate=1600 ratetol=1.0 qcomp=0.60 qpmin=0 qpmax=69 qpstep=4 ip_ratio=1.40 aq=1:1.00
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_video_transpose.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: h264 (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709, progressive), 360x640, q=2-31, 1600 kb/s, 24 fps, 12288 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
      encoder         : Lavc59.1.101 libx264
    Side data:
      cpb: bitrate max/min/avg: 0/0/1600000 buffer size: 0 vbv_delay: N/A
  Stream #0:1(eng): Audio: aac (LC) (mp4a / 0x6134706D), 22050 Hz, stereo, fltp, 128 kb/s (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Sound Media Handler
      vendor_id       : [0][0][0][0]
      encoder         : Lavc59.1.101 aac
frame= 1440 fps= 19 q=-1.0 Lsize=   12586kB time=00:01:00.13 bitrate=1714.4kbits/s speed=0.804x
video:11654kB audio:898kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.268311%
[libx264 @ 000001f530e50340] frame I:10    Avg QP:10.83  size: 69574
[libx264 @ 000001f530e50340] frame P:709   Avg QP:13.17  size: 12891
[libx264 @ 000001f530e50340] frame B:721   Avg QP:17.63  size:  2909
[libx264 @ 000001f530e50340] consecutive B-frames: 30.6%  5.0%  9.2% 55.3%
[libx264 @ 000001f530e50340] mb I  I16..4: 11.3% 23.3% 65.4%
[libx264 @ 000001f530e50340] mb P  I16..4:  1.2%  3.4%  1.9%  P16..4: 25.6% 20.8% 16.3%  0.0%  0.0%    skip:30.8%
[libx264 @ 000001f530e50340] mb B  I16..4:  0.9%  0.8%  0.4%  B16..8: 27.7% 17.5%  8.5%  direct: 5.9%  skip:38.4%  L0:55.3% L1:36.6% BI: 8.1%
[libx264 @ 000001f530e50340] final ratefactor: 13.59
[libx264 @ 000001f530e50340] 8x8 transform intra:45.1% inter:29.3%
[libx264 @ 000001f530e50340] coded y,uvDC,uvAC intra: 52.5% 61.4% 50.6% inter: 20.1% 25.6% 10.1%
[libx264 @ 000001f530e50340] i16 v,h,dc,p: 56% 30%  9%  5%
[libx264 @ 000001f530e50340] i8 v,h,dc,ddl,ddr,vr,hd,vl,hu: 48% 17% 18%  2%  2%  3%  3%  3%  3%
[libx264 @ 000001f530e50340] i4 v,h,dc,ddl,ddr,vr,hd,vl,hu: 31% 19% 13%  6%  6%  6%  6%  6%  6%
[libx264 @ 000001f530e50340] i8c dc,h,v,p: 56% 16% 23%  6%
[libx264 @ 000001f530e50340] Weighted P-Frames: Y:3.1% UV:1.8%
[libx264 @ 000001f530e50340] ref P L0: 86.3%  6.0%  6.3%  1.3%  0.0%
[libx264 @ 000001f530e50340] ref B L0: 96.1%  3.5%  0.3%
[libx264 @ 000001f530e50340] ref B L1: 98.3%  1.7%
[libx264 @ 000001f530e50340] kb/s:1591.06
[aac @ 000001f530da16c0] Qavg: 35051.918
```

```-视频裁剪
-视频裁剪
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -an -vf "crop=640:360:120:0" -vcodec libx264 -b:v 100k Z:\9_ffmpeg\videos\output_video_crop_100.mp4
-an 把声音砍掉
-b:v 比特率（数据速率）（每帧图片质量，超过原视频的也不好）
-crop=w:h:x:y w,h为输出视频的宽高，x,y为视频中某点，向右下进行裁剪；如果不写默认居中裁剪
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
File 'Z:\9_ffmpeg\videos\output_video_crop.mp4' already exists. Overwrite? [y/N] y
Stream mapping:
  Stream #0:1 -> #0:0 (h264 (native) -> h264 (libx264))
Press [q] to stop, [?] for help
[libx264 @ 000001bb738300c0] using cpu capabilities: MMX2 SSE2Fast SSSE3 SSE4.2 AVX FMA3 BMI2 AVX2
[libx264 @ 000001bb738300c0] profile High, level 3.0, 4:2:0, 8-bit
[libx264 @ 000001bb738300c0] 264 - core 163 r3059 b684ebe - H.264/MPEG-4 AVC codec - Copyleft 2003-2021 - http://www.videolan.org/x264.html - options: cabac=1 ref=3 deblock=1:0:0 analyse=0x3:0x113 me=hex subme=7 psy=1 psy_rd=1.00:0.00 mixed_ref=1 me_range=16 chroma_me=1 trellis=1 8x8dct=1 cqm=0 deadzone=21,11 fast_pskip=1 chroma_qp_offset=-2 threads=11 lookahead_threads=1 sliced_threads=0 nr=0 decimate=1 interlaced=0 bluray_compat=0 constrained_intra=0 bframes=3 b_pyramid=2 b_adapt=1 b_bias=0 direct=1 weightb=1 open_gop=0 weightp=2 keyint=250 keyint_min=24 scenecut=40 intra_refresh=0 rc_lookahead=40 rc=abr mbtree=1 bitrate=600 ratetol=1.0 qcomp=0.60 qpmin=0 qpmax=69 qpstep=4 ip_ratio=1.40 aq=1:1.00
Output #0, mp4, to 'Z:\9_ffmpeg\videos\output_video_crop.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: h264 (avc1 / 0x31637661), yuv420p(tv, smpte170m/smpte170m/bt709, progressive), 640x360, q=2-31, 600 kb/s, 24 fps, 12288 tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
      encoder         : Lavc59.1.101 libx264
    Side data:
      cpb: bitrate max/min/avg: 0/0/600000 buffer size: 0 vbv_delay: N/A
frame= 1440 fps= 53 q=-1.0 Lsize=    4378kB time=00:00:59.87 bitrate= 598.9kbits/s speed= 2.2x
video:4364kB audio:0kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.314151%
[libx264 @ 000001bb738300c0] frame I:9     Avg QP:19.75  size: 38372
[libx264 @ 000001bb738300c0] frame P:801   Avg QP:22.38  size:  4375
[libx264 @ 000001bb738300c0] frame B:630   Avg QP:27.01  size:   981
[libx264 @ 000001bb738300c0] consecutive B-frames: 38.9%  5.6%  8.3% 47.2%
[libx264 @ 000001bb738300c0] mb I  I16..4: 11.5% 26.4% 62.1%
[libx264 @ 000001bb738300c0] mb P  I16..4:  1.2%  2.5%  1.0%  P16..4: 33.2% 11.9%  6.3%  0.0%  0.0%    skip:44.0%
[libx264 @ 000001bb738300c0] mb B  I16..4:  1.0%  0.8%  0.1%  B16..8: 38.7%  5.9%  1.5%  direct: 2.5%  skip:49.4%  L0:50.4% L1:43.9% BI: 5.7%
[libx264 @ 000001bb738300c0] final ratefactor: 22.57
[libx264 @ 000001bb738300c0] 8x8 transform intra:46.4% inter:43.8%
[libx264 @ 000001bb738300c0] coded y,uvDC,uvAC intra: 42.1% 51.4% 27.1% inter: 11.3% 13.2% 1.7%
[libx264 @ 000001bb738300c0] i16 v,h,dc,p: 41% 33% 14% 12%
[libx264 @ 000001bb738300c0] i8 v,h,dc,ddl,ddr,vr,hd,vl,hu: 29% 22% 32%  2%  3%  3%  3%  3%  3%
[libx264 @ 000001bb738300c0] i4 v,h,dc,ddl,ddr,vr,hd,vl,hu: 25% 17% 15%  6%  7%  7%  7%  7%  7%
[libx264 @ 000001bb738300c0] i8c dc,h,v,p: 57% 24% 13%  6%
[libx264 @ 000001bb738300c0] Weighted P-Frames: Y:2.2% UV:1.5%
[libx264 @ 000001bb738300c0] ref P L0: 80.7%  8.5%  9.0%  1.8%  0.0%
[libx264 @ 000001bb738300c0] ref B L0: 94.1%  5.4%  0.5%
[libx264 @ 000001bb738300c0] ref B L1: 97.1%  2.9%
[libx264 @ 000001bb738300c0] kb/s:595.71
```

```
-RGBA格式的数据转化为 JPEG格式的图片
ffmpeg -f rawvideo -pix_fmt rgba -s 480*480 -i texture.rgb -f image2 -vcodec mjpeg output.jpg
```

```
-将一个YUV格式表示的数据转换为JPEG格式的图片
ffmpeg -f rawvideo -pix_fmt yuv420p -s 480*480 -i texture.yuv -f image2 -vcodec mjpeg output.jpg
```

```
-将一段视频推送到流媒体服务器上
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -re -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -acodec copy -vcodec copy   -f flv rtmp://172.17.1.18:1935/live/tt0

rtmp://172.17.1.18:1935/live/tt0 播放地址
（需要自己搭建流媒体服务器）

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
      handler_name    : hint media handler
Output #0, flv, to 'rtmp://172.17.1.18:1935/live/tt0':
  Metadata:
    major_brand     : mp42
    minor_version   : 1
    compatible_brands: mp42avc1
    encoder         : Lavf59.3.100
  Stream #0:0(eng): Video: h264 (Constrained Baseline) ([7][0][0][0] / 0x0007), yuv420p(tv, smpte170m/smpte170m/bt709), 640x360, q=2-31, 612 kb/s, 23.96 fps, 24 tbr, 1k tbn (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Video Media Handler
      vendor_id       : [0][0][0][0]
  Stream #0:1(eng): Audio: aac (LC) ([10][0][0][0] / 0x000A), 22050 Hz, stereo, fltp, 65 kb/s (default)
    Metadata:
      creation_time   : 2010-02-09T01:55:39.000000Z
      handler_name    : Apple Sound Media Handler
      vendor_id       : [0][0][0][0]
Stream mapping:
  Stream #0:1 -> #0:0 (copy)
  Stream #0:0 -> #0:1 (copy)
Press [q] to stop, [?] for help
[flv @ 000002cdb6966940] Failed to update header with correct duration.0.0kbits/s speed=   1x
[flv @ 000002cdb6966940] Failed to update header with correct filesize.
frame= 1440 fps= 24 q=-1.0 Lsize=    5019kB time=00:01:00.14 bitrate= 683.6kbits/s speed=   1x
video:4491kB audio:478kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 1.008274%
```

```
-将流媒体服务器上的流dump到本地
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -i http://172.17.1.147:9999/9_ffmpeg/videos/testflv.flv -acodec copy -vcodec copy -f flv Z:\9_ffmpeg\videos\output_video_dump_local.flv

Input #0, flv, from 'http://172.17.1.147:9999/9_ffmpeg/videos/testflv.flv':
  Metadata:
    encoder         : Lavf54.6.100
  Duration: 00:02:04.76, start: 0.080000, bitrate: 1803 kb/s
  Stream #0:0: Video: h264 (Main), yuv420p(progressive), 800x450 [SAR 1:1 DAR 16:9], 1600 kb/s, 25 fps, 25 tbr, 1k tbn
  Stream #0:1: Audio: aac (LC), 44100 Hz, stereo, fltp, 96 kb/s
File 'Z:\9_ffmpeg\videos\output_video_dump_local.flv' already exists. Overwrite? [y/N] y
Output #0, flv, to 'Z:\9_ffmpeg\videos\output_video_dump_local.flv':
  Metadata:
    encoder         : Lavf59.3.100
  Stream #0:0: Video: h264 (Main) ([7][0][0][0] / 0x0007), yuv420p(progressive), 800x450 [SAR 1:1 DAR 16:9], q=2-31, 1600 kb/s, 25 fps, 25 tbr, 1k tbn
  Stream #0:1: Audio: aac (LC) ([10][0][0][0] / 0x000A), 44100 Hz, stereo, fltp, 96 kb/s
Stream mapping:
  Stream #0:0 -> #0:0 (copy)
  Stream #0:1 -> #0:1 (copy)
Press [q] to stop, [?] for help
[http @ 0000024c06f4fcc0] Stream ends prematurely at 1212416, should be 28129536s speed=0.478x
    Last message repeated 1 times
[flv @ 0000024c06f4f000] Packet corrupt (stream = 0, dts = 4200).
[NULL @ 0000024c06f53480] Invalid NAL unit size (27264 > 15449).
[NULL @ 0000024c06f53480] missing picture in access unit with size 15453
[http @ 0000024c06f4fcc0] Stream ends prematurely at 1212416, should be 28129536
http://172.17.1.147:9999/9_ffmpeg/videos/testflv.flv: I/O error
frame=  100 fps= 21 q=-1.0 Lsize=    1184kB time=00:00:04.13 bitrate=2346.8kbits/s speed=0.874x
video:1129kB audio:50kB subtitle:0kB other streams:0kB global headers:0kB muxing overhead: 0.452535%

问题：报错了，输出视频不完整
```

```
将两个音频文件以两路流的形式封装到一个文件中（原唱，伴唱）
ffmpeg -i 131.mp3 -i 134.mp3 -map 0:a -c:a:0 libfdk_aac -b:a:0 96k -map 1:a -c:a:1 libfdk_aac -b:a:1 64k -vn -f mp4 output.m4a
问题：缺少libsdk_aac库，无法尝试
```

