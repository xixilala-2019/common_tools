package com.xixilala.adapteui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/***
 * 刘海屏适配
 * 适配步骤
 * 1.判断手机厂商
 * 2.判断是否有刘海
 * 3.设置是否让内容区域延伸到刘海区
 * 4.设置控件是否避开刘海
 * 5.获取刘海高度
 */
public class DisplayCutoutAdapte extends Activity {
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = initView();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
    
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT = 0; // 内容向下放
//            LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER = 2;// 内容向下放
//            LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES = 1; // 内容扩展到刘海区域
            
            attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            
            window.setAttributes(attributes);
        }
        
        
    
    
        setContentView(view);
        
        

    }

    private View initView() {
        FrameLayout frameLayout = new FrameLayout(this);
        ImageView image = new ImageView(this);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        image.setBackgroundColor(Color.CYAN);
        frameLayout.addView(image);
    
    
        button = new Button(this);
        button.setText("button");
       
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (hasDisplayCutout(this)) {
                params.topMargin = (int) getDisplayCutoutHeight(this);
            }
        }
        button.setLayoutParams(params);
          
        frameLayout.addView(button);
        return frameLayout;
    }

    
    public static boolean hasDisplayCutout(Activity activity) {
 
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            WindowInsets rootWindowInsets;
            rootWindowInsets = decorView.getRootWindowInsets();
            if (rootWindowInsets != null ) {
                DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
                if (displayCutout != null
                        && displayCutout.getBoundingRects() != null
                        && displayCutout.getBoundingRects().size() > 0
                        && displayCutout.getSafeInsetTop() > 0) {
                    return true;
                }
            }
        }
        
        return true; // 模拟器 getRootWindowInsets 返回空
    }
    
    /**
     * 一般情况刘海高度和状态栏一样高
     */
    public static float getDisplayCutoutHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if(identifier > 0) {
            return resources.getDimension(identifier);
        } else {
            return -1;
        }
    
    }

}
