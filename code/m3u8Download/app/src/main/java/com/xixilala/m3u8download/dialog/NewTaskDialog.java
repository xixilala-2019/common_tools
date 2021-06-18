package com.xixilala.m3u8download.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.xixilala.m3u8download.R;
import com.xixilala.m3u8download.parser.ParserUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NewTaskDialog {

    private static final String TAG = "M3U8er";

    private final Activity activity;

    public NewTaskDialog(Activity activity) {
        this.activity = activity;
    }

    private Thread gettingNameThread = null;

    public void show(String sharedUrl) {
        View view = activity.getLayoutInflater().inflate(R.layout.new_task_dialog, null);
        final EditText urlTxt = view.findViewById(R.id.url);
        final EditText nameTxt = view.findViewById(R.id.name);
        final EditText pathTxt = view.findViewById(R.id.path);

        // 点击名称输入框自动填写名称
        nameTxt.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if(hasFocus) {
                if(!nameTxt.getText().toString().isEmpty()) return;
                String url = urlTxt.getText().toString();
                if(url.isEmpty() || !url.startsWith("http")) return;
                setVideoName(url, nameTxt);
            } else
                nameTxt.setHint(R.string.click_me_to_get_name);
        });

        // 设置对话框标题、试图、按钮点击事件等
        AlertDialog newTaskDialog = new AlertDialog.Builder(activity)
                .setTitle("新建下载任务")
                .setView(view)
                .setNegativeButton("取消", (DialogInterface dialog, int which) -> dialog.dismiss())
                .setPositiveButton("开始", (DialogInterface dialog, int which) -> {
                    String url = urlTxt.getText().toString();
                    if(!url.startsWith("http")) {
                        Toast.makeText(activity,R.string.error_http_input, Toast.LENGTH_LONG).show();
                        return;
                    }

                    // 若未指定视频名称则默认设置为当前时间.mp4
                    if(nameTxt.getText().toString().isEmpty()) {
                        String name = SimpleDateFormat.getDateTimeInstance()
                                .format(new Date(System.currentTimeMillis()));
                        name += ".mp4";
                        nameTxt.setText(name);
                        Toast.makeText(activity,
                                R.string.video_name_not_specified + name,
                                Toast.LENGTH_SHORT).show();
                    }

                    // Todo
                    //  新建任务
                }).create();

        newTaskDialog.show();

        // 设置链接、名称
        if(sharedUrl != null) {
            urlTxt.setText(sharedUrl);
            setVideoName(sharedUrl, nameTxt);
        }
    }

    /**
     * 设置视频的名字
     * @param url 视频链接地址
     * @param nameTxt 视频名字所要显示的TextView
     */
    private void setVideoName(final String url, final EditText nameTxt) {
        nameTxt.setHint(R.string.getting_name);
        // 中断上一个获取名称的线程
        if(gettingNameThread != null) {
            Log.d(TAG, "Interrupt getting name thread");
            gettingNameThread.interrupt();
        }
        Log.d(TAG, "Start to get name...");
        gettingNameThread = new Thread(() -> {
            String name = ParserUtil.getVideoName(url);
            if(nameTxt.getVisibility() != View.VISIBLE) return; // 控件不可见则不更新名称
            if(name != null) {
                Log.d(TAG, "Name: " + name);
                activity.runOnUiThread(()-> nameTxt.setText(name));
            }
            else {  // 无法获取
                Log.d(TAG, "Unable to get name");
                activity.runOnUiThread(()-> nameTxt.setHint(R.string.unable_to_get_name));
            }
        });
        gettingNameThread.start();
    }
}
