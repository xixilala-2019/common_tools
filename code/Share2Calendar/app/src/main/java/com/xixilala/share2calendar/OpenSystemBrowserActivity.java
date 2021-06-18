package com.xixilala.share2calendar;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class OpenSystemBrowserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAtBrowser(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        openAtBrowser(intent);
    }

    private void openAtBrowser(Intent intent) {
        CharSequence result = intent != null ? intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT") : null;
        try {
            Intent openBrowserIntent = new Intent();
            openBrowserIntent.setAction(Intent.ACTION_WEB_SEARCH);
            openBrowserIntent.putExtra(SearchManager.QUERY, result);
            startActivity(openBrowserIntent);
            Toast.makeText(getApplicationContext(),"搜索成功", Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"搜索失败", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
