package com.e_lliam.app.video.server.utils;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-11-5
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
public class GetTencentMp4Path {
    public static void main(String[] args) {
//        String tencentUrl = "http://v.qq.com/cover/s/so3o7oso695y4ra/m0013zklasy.html";
        String tencentUrl ="http://v.qq.com/cover/2/2jjtj6a2ney7meq.html?vid=x0013vn307f";
//        String tencentUrl = "http://film.qq.com/cover/9/9xmc61a1o3okc4b.html";
        System.out.println(getVideoId(tencentUrl));
    }

    public static List<String> getTencentUrl(String url) {
        String domain = "http://vv.video.qq.com/geturl?vid=" + getVideoId(url) + "&otype=xml&platform=1&ran=0%2E9652906153351068";
        List<String> list = Lists.newArrayList();
        String content = getWebCon(domain);
        String[] s = content.split("<url>");
        int index = s[3].indexOf("level=1");
        list.add(s[3].substring(0, index + 7));
        return list;
    }

    public static String getVideoId(String url) {
        String vid = "";
        if (url.contains("vid=")){
            int index = url.indexOf("vid=")+4;
            vid = url.substring(index,url.length());
        } else{
            String content = getWebCon(url);
            int index = content.indexOf("vid:");
            vid = content.substring(index+5,index+16);
        }
        return vid;
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
}
