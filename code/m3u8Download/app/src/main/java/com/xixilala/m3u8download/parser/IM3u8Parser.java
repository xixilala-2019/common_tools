package com.xixilala.m3u8download.parser;

import androidx.annotation.NonNull;

public interface IM3u8Parser {

    /**
     * 返回解析器名
     */
    @NonNull
    String name();

    /**
     * 返回解析器的描述信息
     */
    @NonNull
    String description();

    /**
     * 返回解析器版本号
     */
    int versionCode();

    /**
     * 返回解析器版本名
     */
    @NonNull
    String versionName();

    /**
     * 返回解析器作者
     */
    @NonNull
    String author();

    /**
     * 返回支持解析的Url前缀列表
     */
    @NonNull
    String[] supportedUrlPrefixes();

    /**
     * 根据分享地址获取视频名称
     * @param sharedUrl 分享地址
     * @return 视频名称。返回null表示无法获取到名称
     */
    String getVideoName(@NonNull String sharedUrl);

    /**
     * 根据分享地址获取真实的m3u8地址
     * @param sharedUrl 分享地址
     * @return 真实的m3u8地址。返回null表示无法获取到地址
     */
    String getM3u8Url(@NonNull String sharedUrl);
}
