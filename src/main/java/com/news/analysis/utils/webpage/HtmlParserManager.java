package com.news.analysis.utils.webpage;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.news.analysis.pojo.ParserResultEntity;
import org.springframework.security.access.intercept.AfterInvocationManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据爬取管理器
 */
public class HtmlParserManager {
    public static List<ParserResultEntity> parseHtml(String html, String time, String siteName, String newsType) {
        List<ParserResultEntity> dataList = new ArrayList<>();
        // 先把ul拿到
        String regex = "<ul[\\s\\S]*?</ul>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String ulBlockHtmlSrouce = matcher.group();
            // 匹配li
            String regexLi = "<li[\\s\\S]*?</li>";
            pattern = Pattern.compile(regexLi);
            matcher = pattern.matcher(ulBlockHtmlSrouce);
            while (matcher.find()) {
                ParserResultEntity resultEntity = new ParserResultEntity();
                String liHtmlSource = matcher.group();
                String regexFont = "<font>([\\s\\S]*?)</font>";
                Pattern fontPattern = Pattern.compile(regexFont);
                Matcher fontMatcher = fontPattern.matcher(liHtmlSource);
                if (fontMatcher.find()) {
                    String font = fontMatcher.group(1);
                    // 已经有的数据不封装
                    if (StrUtil.isBlank(time) || DateUtil.parse(font).getTime() > DateUtil.parse(time).getTime()) {
                        resultEntity.setSiteName(siteName);
                        resultEntity.setNewsType(newsType);
                        resultEntity.setTime(font);
                        // 解析新闻地址
                        String regexUrl = "href=\"([\\s\\S]*?)\"";
                        Pattern urlPattern = Pattern.compile(regexUrl);
                        Matcher urlMatcher = urlPattern.matcher(liHtmlSource);
                        if (urlMatcher.find()) {
                            resultEntity.setNewsUrl(urlMatcher.group(1));
                        }
                        // 解析新闻标题
                        String regexTitle = "<a[\\s\\S]*?>([\\s\\S]*?)</a>";
                        Pattern titlePattern = Pattern.compile(regexTitle);
                        Matcher titleMatcher = titlePattern.matcher(liHtmlSource);
                        if (titleMatcher.find()) {
                            resultEntity.setTitle(titleMatcher.group(1));
                        }
                        dataList.add(resultEntity);
                    }
                }
            }
        }
        return dataList;
    }
}
