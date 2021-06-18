package com.xixilala.m3u8download.parser;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class ParserUtil {
    private static String TAG = "ParserUtil";

    private static Map<String, String> prefix2parser;

    /*
     * 初始化解析器工具类
     */
    static {
        prefix2parser = new HashMap<>();
        String[] parserNames = new String[] {
                JikeParser.class.getName()
        };

        for(String parserName : parserNames) {
            try {
                IM3u8Parser parser = (IM3u8Parser) Class.forName(parserName).newInstance();
                for(String prefix : parser.supportedUrlPrefixes()) {
                    prefix2parser.put(prefix, parserName);
                }
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
        }
    }

    /**
     * 获取可以解析sharedUrl的解析器
     * @param sharedUrl 分享的地址
     * @return 解析器实例。返回null表示没有相应的解析器
     */
    public static IM3u8Parser getParserInstance(String sharedUrl) {
        if(sharedUrl == null || sharedUrl.isEmpty()) return null;
        for(Map.Entry<String, String> entry: prefix2parser.entrySet()) {
            if(sharedUrl.startsWith(entry.getKey())) {
                try {
                    return (IM3u8Parser) Class.forName(entry.getValue()).newInstance();
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        }

        // 返回默认解析器
        return new DefaultParser();
    }

    /**
     * 获取shared对应的视频名字
     * @param sharedUrl 分享的地址
     * @return 视频名字。返回null表示没有获取到名字
     */
    public static String getVideoName(String sharedUrl) {
        if(sharedUrl == null || sharedUrl.isEmpty()) return null;
        return getParserInstance(sharedUrl).getVideoName(sharedUrl);
    }

    /**
     * 获取shared对应的m3u8地址
     * @param sharedUrl 分享的地址
     * @return m3u8地址。返回null表示没有获取到m3u8地址
     */
    public static String getM3u8Url(String sharedUrl) {
        if(sharedUrl == null || sharedUrl.isEmpty()) return null;
        return getParserInstance(sharedUrl).getM3u8Url(sharedUrl);
    }
}
