package com.kai.api.service.impl;

import com.kai.api.common.ArticleType;
import com.kai.api.common.BaseResponseBody;
import com.kai.api.common.Language;
import com.kai.api.common.Page;
import com.kai.api.common.exception.NotFoundException;
import com.kai.api.model.Article;
import com.kai.api.repository.ArticleRepository;
import com.kai.api.service.ArticleService;
import com.kai.api.service.translate.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TranslateService translateService;

    @Override
    public BaseResponseBody<List<Article>> indexArticle() {
        return new BaseResponseBody<List<Article>>().setData(articleRepository.indexArticle());
    }

    @Override
    public BaseResponseBody<Page<Article>> getArticleByType(ArticleType articleType, Integer pageNo, Integer pageSize) {

        final org.springframework.data.domain.Page<Article> pageResult = articleRepository
                .findAllByArticleTypeOrderByCreateTimeDesc(articleType, PageRequest.of(pageNo, pageSize));
        final Page<Article> articlePage = new Page<>();
        articlePage.setTotal(pageResult.getTotalElements()).setData(pageResult.getContent());
        return new BaseResponseBody<Page<Article>>().setData(articlePage);
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
    public BaseResponseBody<Map<Language, String>> translate(Language from, String message) {
        Map<Language, String> resultMap = new HashMap<>();
        for (Language language : Language.values()) {
            if (from == language) {
                continue;
            }
            try{
                String translate = translateService.translate(message, from, language);
                resultMap.put(language, translate);
            }catch (Exception e){
                log.error("翻译失败，from: {} ,to:{} ,message:{} ",from.name(),language.name(),message);
            }
        }
        return new BaseResponseBody<Map<Language, String>>().setData(resultMap);
    }
}
