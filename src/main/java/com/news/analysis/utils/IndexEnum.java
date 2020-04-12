package com.news.analysis.utils;

import java.util.List;

public enum IndexEnum {
    INDEX("index.htm"),
    INDEX_1("index_1.htm"),
    INDEX_2("index_2.htm"),
    INDEX_3("index_3.htm"),
    INDEX_4("index_4.htm"),
    INDEX_5("index_5.htm"),
    INDEX_6("index_6.htm"),
    INDEX_7("index_7.htm"),
    INDEX_8("index_8.htm"),
    INDEX_9("index_9.htm");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    IndexEnum(String value){
        this.value = value;
    }
}
