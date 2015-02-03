package com.e_lliam.app.video.server.utils;

import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONObject;

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
public class GetAiqiyiMp4Path {
    public static void main(String[] args){
        getIQIYIUrl("http://www.iqiyi.com/v_19rrhfwf6w.html");
    }
    public static String getIQIYIMobileUrl(String url)
    {
        try {
            String content =  getWebCon(url);
            int beginLocal = content.indexOf("{");
            int endLocal = content.indexOf("};");
            String JsonContent = content.substring(beginLocal,endLocal+1);
            JSONObject jsobj= new JSONObject(JsonContent);
            if(!jsobj.getString("code").equals("A00000"))
            {
                return null;
            }
            JSONObject jsonData = jsobj.getJSONObject("data");
            return jsonData.getString("l");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }


    public static List<String> getIQIYIUrl(String url) {
        List<String> list = Lists.newArrayList();
        try {
            String tvid = "";
            String curid = "curid=";
            int beginLocal = 0;
            int endLocal = 0;
            String content = "";
            beginLocal = url.indexOf(curid);
            if (beginLocal >= 0) {
                endLocal = url.indexOf("_", beginLocal);
                tvid = url.substring(beginLocal + curid.length(), endLocal);
                System.out.println(tvid);
            } else {
                content = getWebCon(url);
                String tvidStr = "data-player-tvid=\"";
                beginLocal = content.indexOf(tvidStr);

                endLocal = content.indexOf("\"", beginLocal + tvidStr.length());
                tvid = content.substring(beginLocal + tvidStr.length(),
                        endLocal);

            }
            String videoUrl ="http://cache.m.iqiyi.com/jp/qmt/"+tvid+"/?callback=jsonp_callbacks.cbxd94rc";
            System.out.println(videoUrl);
            content = getWebCon(videoUrl);
            beginLocal = content.indexOf("({");
            endLocal = content.indexOf(");");
            String jsonContent = content.substring(beginLocal+1, endLocal);
            String timeUrl =  "http://data.video.qiyi.com/v.ts";
            String timeContent = getWebCon(timeUrl);
            beginLocal = timeContent.indexOf("{");
            endLocal = timeContent.indexOf("};");
            String timeJson = timeContent.substring(beginLocal, endLocal+1);
            JSONObject jsobjTime = new JSONObject(timeJson);
            if(!jsobjTime.getString("code").equals("A00000"))
            {
                return list;
            }
            JSONObject jsonData = jsobjTime.getJSONObject("data");
            String time = jsonData.getString("time");

            int v = Integer.parseInt(time)^1720301010;
            String strV =String.valueOf(v);

            JSONObject jsobjVideo= new JSONObject(jsonContent);

            JSONObject jsonDataVideo = jsobjVideo.getJSONObject("data");

            JSONArray jsonarrVideo = jsonDataVideo.getJSONArray("mpl");
            String mp4Url ="";
            int vd =0;
            String viewName="";
            if (jsonarrVideo.length() > 0) {

                for (int i = 0; i < jsonarrVideo.length(); i++) {

                    JSONObject jsonMp4 = jsonarrVideo.getJSONObject(i);
                    System.out.println(jsonMp4.toString());
                    mp4Url = jsonMp4.getString("m4u");
                    mp4Url += "?v="+strV;
                    mp4Url = getIQIYIMobileUrl(mp4Url);
                    System.out.println(mp4Url);
                    vd =  jsonMp4.getInt("vd");
                    if(vd==1)
                        viewName ="标清";
                    else if(vd==2){
                        viewName ="高清";
                        list.add(mp4Url);
                    }
                    else if(vd==96)
                        viewName ="极速";
//                    System.out.println(viewName + " " +mp4Url);
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
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
