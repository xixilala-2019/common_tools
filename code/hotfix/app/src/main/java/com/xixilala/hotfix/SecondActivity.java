package com.xixilala.hotfix;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meituan.robust.patch.RobustModify;
import com.meituan.robust.patch.annotaion.Add;
import com.meituan.robust.patch.annotaion.Modify;


public class SecondActivity extends Activity {

    @Modify
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); TextView text = new TextView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        text.setLayoutParams(layoutParams);
        text.setText(getNewString0());

        setContentView(text);

        Menu menu = new Menu
    }

    //    @Add
//    @Modify
    public String getTextInfo() {
//        return "bug show";
//        RobustModify.modify();
        return "bug fixed";
//        return text2();
    }

    @Add
    public int getNewString() {
        return R.string.app_name;
    }

    @Add
    public String getNewString0() {
        return "this is new fixed";
    }
}