package com.kai.api.service;

import com.kai.api.common.ArticleType;
import com.kai.api.common.BaseResponseBody;
import com.kai.api.common.Language;
import com.kai.api.common.Page;
import com.kai.api.model.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    BaseResponseBody<List<Article>> indexArticle();

    BaseResponseBody<Page<Article>> getArticleByType(ArticleType articleType, Integer pageNo, Integer pageSize);

    BaseResponseBody<Article> articleDetail(String id);

    BaseResponseBody<Void> addArticle(Article article);

    BaseResponseBody<Map<Language, String>> translate(Language from, String message);
}
