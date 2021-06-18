package com.xixilala.m3u8download.download;

import android.os.AsyncTask;

public class DownloadTask extends AsyncTask<TaskInfo, Integer, TaskStatus> {

    private IDownloadListener listener;
    private TaskStatus taskStatus = TaskStatus.PAUSED;
    private TaskInfo task;          // 任务信息
    private int lastProgress = 0;  // 上一次的下载进度

    public DownloadTask(IDownloadListener listener) {
        this.listener = listener;
    }

    /**
     * 后台下载过程
     * @param tasks 任务信息
     * @return 下载结果
     */
    @Override
    protected TaskStatus doInBackground(TaskInfo... tasks) {
        task = tasks[0];
        if(listener == null) listener = new DefaultListener(task);
        listener.onStart();


        return TaskStatus.SUCCESS;
    }

    /**
     * 更新进度
     * @param values values[0]为进度百分比
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if(progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    /**
     * 下载结束时调用此函数
     * @param status 任务状态，及下载结果
     */
    @Override
    protected void onPostExecute(TaskStatus status) {
        switch(status) {
            case SUCCESS:
                listener.onSuccess(); break;
            case FAILED:
                listener.onFailed(); break;
            case PAUSED:
                listener.onPaused(); break;
            case CANCELED:
                listener.onCanceled(); break;
            default:
                break;
        }
    }

    /**
     * 暂停下载
     */
    public void pausedDownload() {
        taskStatus = TaskStatus.PAUSED;
    }

    /**
     * 取消下载
     */
    public void cancelDownload() {
        taskStatus = TaskStatus.CANCELED;
    }
}

