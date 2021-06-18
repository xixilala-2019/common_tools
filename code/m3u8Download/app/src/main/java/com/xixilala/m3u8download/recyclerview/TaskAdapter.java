package com.xixilala.m3u8download.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.xixilala.m3u8download.R;
import com.xixilala.m3u8download.download.TaskInfo;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * viewType--分别为item以及空view
     */
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_EMPTY = 1;

    private List<TaskInfo> taskList;

    class TaskViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView txtName;
        ProgressBar progressBar;
        Button btnStartOrPause;

        TaskViewHolder(View view) {
            super(view);
            this.view = view;
            txtName = view.findViewById(R.id.taskName);
            progressBar = view.findViewById(R.id.progressBar);
            btnStartOrPause = view.findViewById(R.id.startOrPause);
        }
    }

    /**
     * 构造函数
     * @param taskList 下载任务列表
     */
    public TaskAdapter(List<TaskInfo> taskList) {
        this.taskList = taskList;
    }

    /***
     * 获取ItemView的视图类型
     * @return 视图类型
     */
    @Override
    public int getItemViewType(int position) {
        if(taskList.size() == 0) return VIEW_TYPE_EMPTY;
        else return VIEW_TYPE_ITEM;
    }

    /**
     * 根据不同的ViewType返回不同的ViewHolder
     * @param viewType 视图类型
     * @return 相应的ViewHolder
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_EMPTY) {   // 空视图
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_empty, parent, false);
            return new RecyclerView.ViewHolder(view){};
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        if(!(viewHolder instanceof TaskViewHolder)) return;     // 非任务项视图不进行处理
    }

    @Override
    public int getItemCount() {
        // 如果taskList.size()为0的话，只引入一个布局，就是emptyView
        // 那么，这个recyclerView的itemCount为1
        if (taskList.size() == 0) {
            return 1;
        }
        return taskList.size();
    }
}
