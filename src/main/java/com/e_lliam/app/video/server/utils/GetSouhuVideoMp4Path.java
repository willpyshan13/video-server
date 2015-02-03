package com.e_lliam.app.video.server.utils;

import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-11-5
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class GetSouhuVideoMp4Path {
    public static void main (String[] args){
        GetSohuTV("");
    }
    public static List<String> GetSohuTV(String sohuUrl)
    {
        List<String> list = Lists.newArrayList();
        String url = "http://tv.sohu.com/20131021/n388577603.shtml";
        url = url.replace("http://tv", "http://m.tv");
        String content = getWebCon(url);
        String json = getSoHuJson(content);
        try {
            JSONObject jsobj = new JSONObject(json);
            JSONObject jsonUrls =  jsobj.getJSONObject("urls");
            JSONArray jsonmp4s =  jsonUrls.getJSONArray("mp4");
//            System.out.println("标清");
//            String mp40 = jsonmp4s.getString(0);
//            String[] mp40s = mp40.split(",");
//            for(int i=0;i<mp40s.length;i++)
//            {
//                System.out.println(mp40s[i]);
//            }
//
//            System.out.println("高清");
            String mp41 = jsonmp4s.getString(1);
            String[] mp41s = mp41.split(",");
            for(int i=0;i<mp41s.length;i++)
            {
//                System.out.println(mp41s[i]);
                list.add(mp41s[i]);
            }

//            System.out.println("超清");
//            String mp42 = jsonmp4s.getString(2);
//            String[] mp42s = mp42.split(",");
//            for(int i=0;i<mp42s.length;i++)
//            {
//                System.out.println(mp42s[i]);
//            }



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return list;
    }

    public static String getSoHuJson(String content) {

        String Result="";
        String beginStr = "var VideoData = {";
        try {
            int beginLocal = content.indexOf(beginStr);
            int endLocal = content.indexOf("};", beginLocal);
            Result = content.substring(beginLocal+beginStr.length()-1, endLocal+1);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result;
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
