package com.kai.api.service.impl;

import com.kai.api.common.ArticleType;
import com.kai.api.common.BaseResponseBody;
import com.kai.api.common.Language;
import com.kai.api.common.exception.NotFoundException;
import com.kai.api.model.Article;
import com.kai.api.repository.ArticleRepository;
import com.kai.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public BaseResponseBody<List<Article>> indexArticle() {
        return new BaseResponseBody<List<Article>>().setData(articleRepository.indexArticle());
    }

    @Override
    public BaseResponseBody<List<Article>> getArticleByType(ArticleType articleType, Integer pageNo, Integer pageSize) {
        final List<Article> allByArticleTypeOrderByCreateTimeDesc = articleRepository.findAllByArticleTypeOrderByCreateTimeDesc(articleType);
        return new BaseResponseBody<List<Article>>().setData(allByArticleTypeOrderByCreateTimeDesc);
    }

    @Override
    public BaseResponseBody<Article> articleDetail(String id) {
        final Optional<Article> optionalArticle = articleRepository.findById(id);
        Article article;
        if ((article = optionalArticle.orElse(null)) == null) {
            throw new NotFoundException();
        }
        return new BaseResponseBody<Article>().setData(article);
    }

    @Override
    public BaseResponseBody<Void> addArticle(Article article) {
        final Article save = articleRepository.save(article);
        return new BaseResponseBody<Void>().setMessage("添加成功");
    }

    @Override
    public BaseResponseBody<Map<Language, String>> translate(Language sourceLanguage, String message) {
        return null;
    }
}
