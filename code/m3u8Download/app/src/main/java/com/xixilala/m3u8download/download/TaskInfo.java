package com.xixilala.m3u8download.download;

public class TaskInfo {
    // 视频m3u8链接的hashCode
    public int m3u8UrlHashCode = 0;
    // 视频分享链接
    public String sharedUrl = null;
    // 视频的实际m3u8链接
    public String m3u8Url = null;
    // 任务名
    public String name = null;
    // 任务保存路径
    public String path = null;
    // ts所有分段数量
    public int totalTsNum = 0;
    // 已下载的ts分段数量
    public int downloadedTsNum = 0;
    // 当前ts分段已下载长度
    public long curTsDownloadedLength = 0;
    // 任务下载进度
    public int progress = 0;
    // 是否需要转码
    public boolean needTranscode = false;
    // 任务状态
    public TaskStatus status = TaskStatus.PAUSED;

    // 所绑定的下载器
    public DownloadTask downloadTask = null;
    // 所绑定的下载侦听
    public IDownloadListener downloadListener = null;


    TaskInfo() {}

    public TaskInfo(String url, String name, String path) {
        this.sharedUrl = url;
        this.name = name;
        this.path = path;
        this.m3u8UrlHashCode = (url != null ? url.hashCode() : 0);
    }

    public void setDownloadTask(DownloadTask downloadTask) {
        this.downloadTask = downloadTask;
    }

    public void setDownloadListener(IDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    String[] toStringArray() {
        return new String[]{
                String.valueOf(m3u8UrlHashCode),
                sharedUrl,
                m3u8Url,
                name,
                path,
                String.valueOf(totalTsNum),
                String.valueOf(downloadedTsNum),
                String.valueOf(curTsDownloadedLength),
                String.valueOf(needTranscode),
                String.valueOf(progress),
                status.toString()
        };
    }
}
