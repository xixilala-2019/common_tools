package com.xixilala.m3u8download.parser;

import androidx.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JikeParser implements IM3u8Parser {

    private static final String TAG = "JikeParser";

    /**
     * 返回解析器名
     */
    @Override
    @NonNull
    public String name() {
        return "即刻视频解析";
    }

    /**
     * 返回解析器的描述信息
     */
    @Override
    @NonNull
    public String description() {
        return "支持解析即刻动态链接";
    }

    /**
     * 返回解析器版本号
     */
    @Override
    public int versionCode() {
        return 1;
    }

    /**
     * 返回解析器版本名
     */
    @Override
    @NonNull
    public String versionName() {
        return "v1.0";
    }

    /**
     * 返回解析器作者
     */
    @Override
    @NonNull
    public String author() {
        return "樱花小王子";
    }

    /**
     * 返回支持解析的url前缀列表
     */
    @Override
    @NonNull
    public String[] supportedUrlPrefixes() {
        return new String[]{"https://m.okjike.com"};
    }

    /**
     * 根据分享地址获取视频名称
     *
     * @param sharedUrl 分享地址
     * @return 视频名称。返回null表示无法获取到名称
     */
    @Override
    public String getVideoName(@NonNull String sharedUrl){
        String res = newGetRequest(sharedUrl);
        if(res == null) return null;

        if(!res.contains("<title"))     // 不包含title头则不获取标题
            return "";
        res = res.substring(res.indexOf("<title"), res.indexOf("</title>"));
        res = res.substring(res.indexOf('>') + 1);
        if(res.contains(" - 即刻"))       // 去掉即刻字段
            res = res.substring(0, res.indexOf(" - 即刻"));
        if(res.length() > 25)
            res = res.substring(0, 25);
        res += ".mp4";

        Log.d(TAG, "Name: " + res);
        return res;
    }

    /**
     * 根据分享地址获取真实的m3u8地址
     *
     * @param sharedUrl 分享地址
     * @return 真实的m3u8地址。返回null表示无法获取到名称
     */
    @Override
    public String getM3u8Url(@NonNull String sharedUrl){
        Log.d(TAG, "Shared URL: " + sharedUrl);

        String prefix = "https://m.okjike.com/";
        String type = sharedUrl.substring(prefix.length(), sharedUrl.indexOf("s/"));
        if(type.equals("officialMessage"))
            type = "OFFICIAL_MESSAGE";
        else if(type.equals("originalPost"))
            type = "ORIGINAL_POST";

        String id = sharedUrl.substring(prefix.length() + type.length() + 1, sharedUrl.indexOf('?'));
        sharedUrl = "https://app.jike.ruguoapp.com/1.0/mediaMeta/play?type=" + type + "&id=" + id;

        // 请求该网址后返回一个Json，里面含有真实地址
        String m3u8Url;
        m3u8Url = newGetRequest(sharedUrl);
        if(m3u8Url == null) return null;
        int beginIndex = m3u8Url.indexOf("\":\"");  // 返回内容为{"sharedUrl":null}则说明分享的不是视频
        if(beginIndex == -1) {
            Log.d(TAG, "Not vedio");
            return null;
        }

        m3u8Url = m3u8Url.substring(beginIndex + 3, m3u8Url.indexOf("\","));

        Log.d(TAG, "Real URL: " + m3u8Url);
        return m3u8Url;
    }

    /**
     * 新建一个get请求
     * @param requestUrl 请求地址
     * @return 网页返回内容，null表示请求失败
     */
    private String newGetRequest(String requestUrl){
        String result = null;
        HttpURLConnection con = null;

        try {
            URL url = new URL(requestUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(6000);
            con.setReadTimeout(6000);
            con.connect();

            if (con.getResponseCode() == 200) {
                InputStream is = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                result = sb.toString();
                is.close();
                con.disconnect();
            }
        } catch (Exception e) {
            if(e instanceof InterruptedIOException)
                Thread.currentThread().interrupt();
            Log.e(TAG, e.toString());
        } finally {
            if(con != null) con.disconnect();
        }

        return result;
    }
}
