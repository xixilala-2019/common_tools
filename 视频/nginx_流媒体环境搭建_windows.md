## nginx搭建流媒体服务器(windows)

1.下载 nginx 

官网地址：http://nginx.org

我用的是 这个地址，说是集成了 rtmp 协议模块，但是并没有用，可能是我用错了？
nginx-win.ecsds.eu/download/

2.配置conf/nginx.conf

备份nginx-win.conf
使用nginx-win.conf，做修改

http {
	 server {} 中添加配置

}

```
        location /stat {
            rtmp_stat all;
            rtmp_stat_stylesheet stat.xsl;
        }
        location /stat.xsl {
            root nginx-rtmp-module/;
        }
        location /control {
            rtmp_control all;
        } 
```





http {} 下面添加

```
rtmp {

    server {
        listen 1935; #rtmp 端口
        chunk_size 4000;
        application live { #live可以自定义
             live on;
        }
		application hls {
		     live on;
             hls on;
			 hls_path temp/hls;
			 hls_fragment 8s; #八秒切片
        } 
    }

} 
```

3.下载 nginx-rtmp-module 模块

https://github.com/arut/nginx-rtmp-module/

复制到nginx根目录，与 conf，temp并列

并且命名为 nginx-rtmp-module

4.启动 nginx 
nginx -c conf\nginx-win.conf

5.检查 rtmp 是否配置完成

http://localhost:8081/stat
如果出现表格页面，则配置成功（8081为ngnix端口）



使用 ffmpeg 推流到服务器 
Z:\9_ffmpeg\ffmpeg\bin\ffmpeg -re -i Z:\9_ffmpeg\videos\big_buck_bunny.mp4 -acodec copy -vcodec copy   -f flv rtmp://172.17.1.18:1935/live/tt0

使用支持rtmp的播放器播放
rtmp://172.17.1.18:1935/live/tt0

