package com.kai.api.repository;

import com.kai.api.common.ArticleType;
import com.kai.api.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {

    @Query(value = "SELECT * , ROW_NUMBER() over(partition by ArticleType order by createTime desc ) as rn   FROM AA WHERE SHOW_INDEX =TRUE  and rn =1", nativeQuery = true)
    List<Article> indexArticle();

    List<Article> findAllByArticleTypeOrderByCreateTimeDesc(ArticleType articleType);


}
