package com.xixilala.share2calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView var2 = new TextView((Context)this);
        var2.setText(R.string.app_name);
        this.setContentView(var2);
    }
}
