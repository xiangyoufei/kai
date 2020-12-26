package com.kai.api.service.impl;

import com.kai.api.common.BaseResponseBody;
import com.kai.api.common.Language;
import com.kai.api.model.Article;
import com.kai.api.service.ArticleService;

import java.util.List;
import java.util.Map;

public class ArticleServiceImpl implements ArticleService {
    @Override
    public BaseResponseBody<List<Article>> indexArticle() {
        return null;
    }

    @Override
    public BaseResponseBody<List<Article>> getArticleByType() {
        return null;
    }

    @Override
    public BaseResponseBody<String> articleDetail(String id) {
        return null;
    }

    @Override
    public BaseResponseBody<Void> addArticle(Article article) {
        return null;
    }

    @Override
    public BaseResponseBody<Map<Language, String>> translate(Language sourceLanguage, String message) {
        return null;
    }
}
