package com.kai.api.repository;

import com.kai.api.common.ArticleType;
import com.kai.api.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {

    @Query(value = "select * from (SELECT\ta.*,\t@lastType := @temp,\t@temp := a.article_type," +
            "IF ( @lastType = @temp, @rank := @rank + 1, @rank := 1 ) AS rank " +
            "FROM article a, ( SELECT @a := 0, @temp := 0, @rank := 0 ) b " +
            " ORDER BY article_type) tem where tem.rank=1", nativeQuery = true)
    List<Article> indexArticle();

    Page<Article> findAllByArticleTypeOrderByCreateTimeDesc(ArticleType articleType, Pageable pageable);


}
