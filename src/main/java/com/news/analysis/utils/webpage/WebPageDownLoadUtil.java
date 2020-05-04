package com.news.analysis.utils.webpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class WebPageDownLoadUtil {
    public static BufferedReader getBR(String url,String charset) throws Exception{
        URL urlObj = new URL(url);
        String defaultCharset = CharsetDetectorUtil.getCharset(url,charset);
        if(defaultCharset != null){
            charset = defaultCharset;
        }
        InputStream is = urlObj.openStream();
        InputStreamReader isr = new InputStreamReader(is, charset);
        BufferedReader br = new BufferedReader(isr);
        return br;
    }


    public static String getHtmlSource(String url, String charset) throws Exception {
        BufferedReader br = getBR(url,charset);
        StringBuilder stringBuilder = new StringBuilder();
        String temp = null;
        int lineCounter = 0;
        while ((temp = br.readLine()) != null) {
            if (lineCounter > 0) {
                stringBuilder.append("\n");
            }
            lineCounter++;
            stringBuilder.append(temp);
        }
        br.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception{
        String s = getHtmlSource("https://news.china.com/international/","utf-8");
        System.out.println(s);
    }

}
