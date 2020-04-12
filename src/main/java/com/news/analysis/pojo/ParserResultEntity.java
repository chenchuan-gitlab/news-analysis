package com.news.analysis.pojo;

public class ParserResultEntity {
    private Long id;

    private String siteName;

    private String newsType;

    private String title;

    private String newsUrl;

    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ParserResultEntity{" +
                "id=" + id +
                ", siteName='" + siteName + '\'' +
                ", newsType='" + newsType + '\'' +
                ", title='" + title + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
