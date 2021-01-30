package com.kai.api.repository;

import com.kai.api.common.ArticleType;
import com.kai.api.common.Language;
import com.kai.api.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {

    @Query(value = "select b.* from ( select   " +
            "  IF (@x=a.article_type,@rank\\:=@rank+1,@rank\\:=1) as rn,  @x\\:= a.article_type,  a.* " +
            "from article a, (SELECT @rownum\\:=0,@rank\\:=0) init ORDER BY a.article_type )b  where b.rn=1", nativeQuery = true)
    List<Article> indexArticle();

    Page<Article> findAllByArticleTypeOrderByCreateTimeDesc(ArticleType articleType, Pageable pageable);

    Article findFirstByArticleTypeEqualsAndTitleAndAndLanguageEquals(ArticleType articleType, String title, Language language);

    List<Article> findAllByArticleTypeAndTitle(ArticleType articleType, String title);

    void deleteByIdIn(List<String>ids);
}
