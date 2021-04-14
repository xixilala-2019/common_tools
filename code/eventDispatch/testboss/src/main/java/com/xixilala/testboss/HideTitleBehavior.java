package com.xixilala.testboss;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

public class HideTitleBehavior extends CoordinatorLayout.Behavior<View> {

    private float initDy = 0;
    private float initCy = 0;

    public HideTitleBehavior() {
    }

    public HideTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (initDy == 0) {
            initDy = dependency.getY();
            initCy = child.getY();
            Log.d("init cy ","------ " + initCy);
        }

        float dy = dependency.getY();
        float moveD = (dy/initDy)*(child.getHeight()+initCy);

        child.setTranslationY(moveD);

        return true;
    }
}