package com.xixilala.m3u8download.parser;

import androidx.annotation.NonNull;
import android.util.Log;

public class DefaultParser implements IM3u8Parser {

    private static final String TAG = "DafaultParser";

    /**
     * 返回解析器名
     */
    @NonNull
    @Override
    public String name() {
        return "默认解析器";
    }

    /**
     * 返回解析器的描述信息
     */
    @NonNull
    @Override
    public String description() {
        return "只支持m3u8或mp4地址";
    }

    /**
     * 返回解析器版本
     */
    @Override
    public int versionCode() {
        return 1;
    }

    /**
     * 返回解析器版本名
     */
    @NonNull
    @Override
    public String versionName() {
        return "v1.0";
    }

    /**
     * 返回解析器作者
     */
    @NonNull
    @Override
    public String author() {
        return "樱花小王子";
    }

    /**
     * 返回支持解析的url前缀列表
     */
    @NonNull
    @Override
    public String[] supportedUrlPrefixes() {
        return new String[]{"http"};
    }

    /**
     * 根据分享地址获取视频名称
     *
     * @param sharedUrl 分享地址
     * @return 视频名称。返回null表示无法获取到名称
     */
    @Override
    public String getVideoName(@NonNull String sharedUrl) {
        return null;
    }

    /**
     * 根据分享地址获取真实的m3u8地址
     *
     * @param sharedUrl 分享地址
     * @return 真实的m3u8地址。返回null表示无法获取到名称
     */
    @Override
    public String getM3u8Url(@NonNull String sharedUrl) {
        Log.d(TAG, "Shared URL: " + sharedUrl);
        String urlWithoutQuery = "";    // 除去Query参数部分的Url地址
        if(sharedUrl.indexOf('?') != -1)
            urlWithoutQuery = sharedUrl.substring(0, sharedUrl.indexOf('?'));

        // 已.m3u8或.mp4结尾则已是真实地址
        if(urlWithoutQuery.endsWith(".m3u8") || urlWithoutQuery.endsWith(".mp4")) {
            Log.d(TAG, "Real URL: " + sharedUrl);
            return sharedUrl;
        } else {
            Log.d(TAG, "Unknown URL: " + sharedUrl);
            return null;
        }
    }
}
