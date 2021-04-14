package com.xixilala.hotfix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.meituan.robust.Patch;
import com.meituan.robust.PatchExecutor;
import com.meituan.robust.RobustCallBack;
import com.meituan.robust.patch.annotaion.Modify;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap aaa;

        TextView showText = (TextView) findViewById(R.id.showText);

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new ShowDiffDialog().show(getFragmentManager(),"show diff");
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        findViewById(R.id.loadPatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PatchExecutor(MainActivity.this.getApplicationContext(), new PatchManipulateImp(), new RobustCallBack() {
                    @Override
                    public void onPatchListFetched(boolean result, boolean isNet, List<Patch> patches) {
                        Log.e("---patch---", "---onPatchListFetched---");
                    }

                    @Override
                    public void onPatchFetched(boolean result, boolean isNet, Patch patch) {
                        Log.e("---patch---", "---onPatchFetched---");
                    }

                    @Override
                    public void onPatchApplied(boolean result, Patch patch) {
                        Log.e("---patch---", "---onPatchApplied---");
                    }

                    @Override
                    public void logNotify(String log, String where) {
                        Log.e("---patch---", "---logNotify---");
                    }

                    @Override
                    public void exceptionNotify(Throwable throwable, String where) {
                        throwable.printStackTrace();
                        Log.e("---patch---", "---exceptionNotify---" + where);
                    }
                }).start();
            }
        });

    }


}
