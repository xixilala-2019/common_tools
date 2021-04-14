package com.xixilala.hotfix;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ShowDiffDialog extends DialogFragment {

//    @Modify
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Activity activity = getActivity();
        TextView text = new TextView(activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(400,400);
        text.setLayoutParams(layoutParams);
        text.setText(text1());
        return text;
    }
 
//    @Add
//    @Modify
    public String text1() {
        text2();
//        return "bug show";
        return "bug fixed";
//        return text2();
    }
    
//    @Add
    public String text2() {
        return "bug fixed";
    }
    
    
}
