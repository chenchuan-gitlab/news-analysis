package com.news.analysis.utils.webpage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharsetDetectorUtil {

    public static String getCharset(String url,String charset) throws Exception{
        String finalCharset = null;
        URL urlObj = new URL(url);
        URLConnection urlConnection = urlObj.openConnection();
        // 用header来获取url的charset
        Map<String,List<String>> allHeaderMap = urlConnection.getHeaderFields();
        List<String> kvList = allHeaderMap.get("Content-Type");
        if(kvList != null && !kvList.isEmpty()){
            String line = kvList.get(0);
            String[] kvArray = line.split(";");
            for(String kv: kvArray){
                kv = kv.trim();
                String[] eleArray = kv.split(";");
                if(eleArray.length == 2){
                    if(eleArray[0].equals("charset")){
                        finalCharset = eleArray[1].trim();
                    }
                }
            }
        }
        if(finalCharset != null){
            // 用meta取charset的方式
            InputStream is = urlObj.openStream();
            InputStreamReader isr = new InputStreamReader(is, charset);
            BufferedReader br = new BufferedReader(isr);
            String temp = null;
            while((temp = br.readLine()) != null){
                temp = temp.toLowerCase();
                charset = getCharsetValue4Line(temp);
                if(charset != null){
                    finalCharset = charset;
                    break;
                }
                if(temp.contains("</head>")){
                    break;
                }
                br.close();
            }
        }
        return finalCharset;
    }

    public static String getCharsetValue4Line(String line){
        String regex = "charset=\"?(.+?)\"?\\s?/?>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        String charsetValue = null;
        if(matcher.find()){
            charsetValue = matcher.group(1);
        }
        return charsetValue;
    }
}
