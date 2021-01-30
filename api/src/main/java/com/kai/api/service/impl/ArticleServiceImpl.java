package com.kai.api.service.impl;

import com.kai.api.aspect.AuthInterception;
import com.kai.api.common.ArticleType;
import com.kai.api.common.BaseResponseBody;
import com.kai.api.common.Language;
import com.kai.api.common.Page;
import com.kai.api.common.exception.ExistsException;
import com.kai.api.common.exception.NotFoundException;
import com.kai.api.model.Article;
import com.kai.api.repository.ArticleRepository;
import com.kai.api.service.ArticleService;
import com.kai.api.service.translate.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

        final PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, "createTime");
        final org.springframework.data.domain.Page<Article> pageResult = articleRepository
                .findAllByArticleTypeOrderByCreateTimeDesc(articleType, pageRequest);
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
        final String id = article.getId();
        final String loginUser = AuthInterception.loginUser.get();
        if (StringUtils.isEmpty(id)) {
            article.setId(UUID.randomUUID().toString());
            final Article dbArticle = articleRepository
                    .findFirstByArticleTypeEqualsAndTitleAndAndLanguageEquals(article.getArticleType(), article.getId(), article.getLanguage());
            article.setCreateTime(LocalDateTime.now())
                    .setContent(loginUser);
            if (dbArticle != null) {
                throw new ExistsException("文章标题已经存在");
            }
        } else {
            final Optional<Article> dbArticle = articleRepository.findById(id);
            if (!dbArticle.isPresent()) {
                throw new ExistsException("文章不存在");
            }
            // 修改文章，需要指定语言类型
        }
        article.setUpdateTime(LocalDateTime.now())
                .setUpdater(loginUser);
        final Article save = articleRepository.save(article);
        return new BaseResponseBody<Void>().setMessage("添加成功");
    }

    @Override
    public BaseResponseBody<String> translate(Language from, Language to, String message) {
        return new BaseResponseBody<String>().setData(translateService.translate(message, from, to));
    }

    @Override
    public BaseResponseBody<Void> deleteArticle(String id) {
        final Optional<Article> dbArticle = articleRepository.findById(id);
        if (!dbArticle.isPresent()) {
            throw new ExistsException("文章不存在");
        }
        final Article article = dbArticle.get();
        final List<Article> articles = articleRepository.findAllByArticleTypeAndTitle(article.getArticleType(), article.getTitle());
        final List<String> ids = articles
                .stream()
                .map(item -> item.getId())
                .collect(Collectors.toList());
        ids.add(id);
        articleRepository.deleteByIdIn(ids);
        return new BaseResponseBody<Void>().setMessage("删除成功");
    }
}
