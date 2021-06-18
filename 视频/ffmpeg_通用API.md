## FFmpeg通用API

#### 1.avformat_open_input

```
根据文件路径判断文件的格式，进一步判断需要使用那个Demuxer。
比如，文件是flv,那么Demuxer就会使用对应的ff_flv_demuxer，所以对应的关键生命周期的方法read_header\read_packet|read_seek\read_close都会使用该flv的Demuxer中函数指针指定的函数。
```

#### 2.avformat_find_stream_info

```
用于把所有的Stream的MetaData信息填充好。函数内部会先查对应的解码器，然后打开对应的解码器，紧接着会利用Demuxer中的read_packet函数读取一段数据进行解码（解码数据越多，流信息越准确）
<read_packet函数 probe_sized、max_analyze_duration、fps_probe_size共同控制解码数据的长度（数值正比，越大越准确）>
```







