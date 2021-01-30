package com.kai.api.service;

import com.kai.api.common.ArticleType;
import com.kai.api.common.BaseResponseBody;
import com.kai.api.common.Language;
import com.kai.api.common.Page;
import com.kai.api.model.Article;

import java.util.List;

public interface ArticleService {
    BaseResponseBody<List<Article>> indexArticle();

    BaseResponseBody<Page<Article>> getArticleByType(ArticleType articleType, Integer pageNo, Integer pageSize);

    BaseResponseBody<Article> articleDetail(String id);

    BaseResponseBody<Void> addArticle(Article article);

    BaseResponseBody<String> translate(Language from, Language to, String message);

    BaseResponseBody<Void> deleteArticle(String id);
}
