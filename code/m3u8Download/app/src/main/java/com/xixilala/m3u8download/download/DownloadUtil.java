package com.xixilala.m3u8download.download;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DownloadUtil {

    private static final String TAG = "M3U8er";

    public static List<String> getTsUrls(String m3u8Url) {
        if(m3u8Url == null || m3u8Url.isEmpty()) return null;
        List<String> tsUrls = new ArrayList<>();

        // 检测是否是mp4地址
        String urlWithoutQuery = "";    // 除去Query参数部分的Url地址
        if(m3u8Url.indexOf('?') != -1)
            urlWithoutQuery = m3u8Url.substring(0, m3u8Url.indexOf('?'));
        // 当urlWithoutQuery地址以.mp4结束时也是一个mp4地址
        if(urlWithoutQuery.endsWith(".mp4")) {
            Log.d(TAG, "Not m3u8");
            Log.d(TAG, "MP4 URL: " + m3u8Url);
            tsUrls.add(m3u8Url);
            return tsUrls;
        }

        try {
            URL url = new URL(m3u8Url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(6000);
            con.setReadTimeout(6000);
            con.connect();

            if(con.getResponseCode() == 200) {
                InputStream is = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String line;
                while ((line = reader.readLine()) != null) {
                    if(line.isEmpty() || line.startsWith("#")) continue;    // 忽略空行和参数行
                    tsUrls.add(line);
                }
                is.close();
                con.disconnect();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }

        if(tsUrls.size() == 0) return null;

        // ts分段路径为相对路径则加上路径前缀
        if(!tsUrls.get(0).startsWith("http")) {
            String urlPrefix = m3u8Url.substring(0, m3u8Url.lastIndexOf('/') + 1);
            for (int i = 0; i < tsUrls.size(); i++) {
                tsUrls.set(i, urlPrefix + tsUrls.get(i));
                Log.d(TAG, " Ts URL: " + tsUrls.get(i));
            }
        }

        return tsUrls;
    }
}
