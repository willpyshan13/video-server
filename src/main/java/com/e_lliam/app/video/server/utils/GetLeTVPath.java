package com.e_lliam.app.video.server.utils;

import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-11-4
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
public class GetLeTVPath {
    public static void main(String[] args){
        getTudouUrl();
    }

    public static void getTudouUrl() {

        String url="http://www.tudou.com/albumplay/Lqfme5hSolM/2Z1t-nKXifk.html";
        try {
            String content =  getWebCon(url);
            int beginLocal = content.indexOf("itemData={");
            int endLocal = content.indexOf("}");
            content = content.substring(beginLocal, endLocal);

            String iid = getScriptVarByName("iid:", content);
            url = " http://vr.tudou.com/v2proxy/v2?it=" + iid + "&st=52&pw=";
            System.out.println(url);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static List<String> GetLeTV(String url) throws Exception {
//        String url = "http://www.letv.com/ptv/vplay/2160829.html";
        String mainUrl = "";
        String vheight = "";
        String vwidth = "";
        List<String> list =Lists.newArrayList();
        try {
            String content = getWebCon(url);
            String realurl = getLeTvUrl(content);
            // 取得返回的 json数据
            String json = getInputHtml(realurl);

            String json1 = json.substring(7, json.length() - 3);
//            System.out.println(json1);
            JSONObject jsobj = new JSONObject(json1);
            int statusCode = jsobj.getInt("statusCode");
//            System.out.println(statusCode);
            if (statusCode==1001) {
                JSONArray jsonarr = jsobj.getJSONArray("data");
                if (jsonarr.length() > 0) {
                    JSONObject obj1 = jsonarr.getJSONObject(0);
                    JSONArray Infoarr = obj1.getJSONArray("infos");
                    for (int i = 0; i < Infoarr.length(); i++) {

                        JSONObject Infoobj = Infoarr.getJSONObject(i);
                        mainUrl = Infoobj.getString("mainUrl");
                        if (i==1)
                            list.add(mainUrl);
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public static String getLeTvUrl(String content) throws Exception {

        int beginLocal = content.indexOf("video : {");
        int endLocal = content.indexOf("}", beginLocal);
        content = content.substring(beginLocal, endLocal);
        /**
         * 获取视频地址
         */
        String vid = getScriptVarByName("vid:", content);
        String mmsid = getScriptVarByName("mmsid:", content);
        String url = "http://117.121.58.221/geturlv2?platid=3&splatid=301&callback=vjs_99&playid=&vtype=9,13,21,22,28&version=2.0&tss=no&mmsid="
                + mmsid + "&vid=" + vid + "&tkey=9&_r=0";
        return url;
    }

    private static String getScriptVarByName(String name, String content) {
        String script = content;
        int begin = script.indexOf(name);
        script = script.substring(begin + name.length());
        int end = script.indexOf(",");
        script = script.substring(0, end);
        String result = script.replaceAll("'", "");
        result = result.trim();
        return result;
    }

    public static String getWebCon(String domain) {
        StringBuffer sb = new StringBuffer();
        try {
            java.net.URL url = new java.net.URL(domain);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();

        } catch (Exception e) { // Report any errors that arise

            sb.append(e.toString());
            System.err.println(e);
            System.err
                    .println("Usage:   java   HttpClient   <URL>   [<filename>]");
        }
        return sb.toString();

    }

    public static String getInputHtml(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url
                    .openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setConnectTimeout(5 * 1000);
            if (httpsURLConnection.getResponseCode() == 200) {
                // 通过输入流获取网络图片
                InputStream inputStream = httpsURLConnection.getInputStream();
                byte[] data = readInputStream(inputStream);
                inputStream.close();
                return new String(data, "UTF-8");
            }

        } catch (Exception e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static byte[] readInputStream(InputStream inputStream)
            throws Exception {
        // 定义一个输出流向内存输出数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 定义一个缓冲区
        byte[] buffer = new byte[1024];
        // 读取数据长度
        int len = 0;
        // 当取得完数据后会返回一个-1
        while ((len = inputStream.read(buffer)) != -1) {
            // 把缓冲区的数据 写到输出流里面
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.close();
        // 得到数据后返回
        return byteArrayOutputStream.toByteArray();
    }

}
