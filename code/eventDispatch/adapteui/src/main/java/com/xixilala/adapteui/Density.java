package com.xixilala.adapteui;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;



/**
 * 修改app density 进行适配
 */
public class Density {

    public static final float WIDTH = 360; //
    public static float density;
    public static float scaledDensity;

    public static void setDensity(Application application, Activity activity) {

        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if(density == 0) {
            density = appDisplayMetrics.density;
            scaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        // 当系统修改字体大小时，会调用，需要重新设置
                        scaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }


        float targetDensity = appDisplayMetrics.widthPixels / WIDTH;
        float targetScaleDensity = targetDensity * (scaledDensity / density);
        int targetDensityDpi = (int) (targetDensity * 160);

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;


    }
}
