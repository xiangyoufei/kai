package com.kai.api.common;

public enum Language {

    zh("中文", "中国"),
    it("意大利文", "意大利"),
    en("英文", "美国");

    private String language;

    private String country;

    private Language(String language, String country) {
        this.language = language;
        this.country = country;
    }
}
