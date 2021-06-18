package com.xixilala.m3u8download.download;

public interface IDownloadListener {

    /**
     * 开始
     */
    void onStart();

    /**
     * 下载中
     * @param progress 下载进度
     */
    void onProgress(int progress);

    /**
     * 转码中
     */
    void onTranscode();

    /**
     * 成功
     */
    void onSuccess();

    /**
     * 失败
     */
    void onFailed();

    /**
     * 暂停
     */
    void onPaused();

    /**
     * 取消
     */
    void onCanceled();
}
