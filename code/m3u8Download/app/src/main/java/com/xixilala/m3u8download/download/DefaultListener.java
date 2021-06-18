package com.xixilala.m3u8download.download;

import android.util.Log;

public class DefaultListener implements IDownloadListener {

    private static final String TAG = "M3U8er";
    private TaskInfo task;

    DefaultListener(TaskInfo taskInfo) {
        this.task = taskInfo;
    }

    /**
     * 开始
     */
    @Override
    public void onStart() {
        task.status = TaskStatus.DOWNLOADING;
        Log.d(TAG, " Start: " + task.name);
    }

    /**
     * 下载中
     *
     * @param progress 下载进度
     */
    @Override
    public void onProgress(int progress) {
        Log.d(TAG, " Progress " + progress + ": " + task.name);
    }

    /**
     * 转码中
     */
    @Override
    public void onTranscode() {
        task.status = TaskStatus.TRANSCODING;
        Log.d(TAG, " Transcode: " + task.name);
    }

    /**
     * 成功
     */
    @Override
    public void onSuccess() {
        task.status = TaskStatus.SUCCESS;
        Log.d(TAG, " Success: " + task.name);
    }

    /**
     * 失败
     */
    @Override
    public void onFailed() {
        task.status = TaskStatus.FAILED;
        Log.d(TAG, " Failed: " + task.name);
    }

    /**
     * 暂停
     */
    @Override
    public void onPaused() {
        task.status = TaskStatus.PAUSED;
        Log.d(TAG, " Paused: " + task.name);
    }

    /**
     * 取消
     */
    @Override
    public void onCanceled() {
        task.status = TaskStatus.CANCELED;
        Log.d(TAG, " Canceled: " + task.name);
    }
}
