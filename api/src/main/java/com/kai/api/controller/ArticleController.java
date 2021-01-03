package com.kai.api.controller;

import com.kai.api.common.*;
import com.kai.api.model.Article;
import com.kai.api.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article/")
@Api(tags = {"文章"})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("index")
    @ApiOperation("显示首页文章")
    public BaseResponseBody<List<Article>> indexArticle() {
        return articleService.indexArticle();
    }

    @GetMapping("listByType")
    @ApiOperation("根据文章类型查找文章")
    public BaseResponseBody<Page<Article>> getArticleByType(
            @RequestParam @NotNull(message = "文章类型不能为空") @ApiParam("文章类型") ArticleType articleType,
            @RequestParam(required = false, defaultValue = "1") @ApiParam("页码") Integer pageNo,
            @RequestParam(required = false, defaultValue = "12") @ApiParam("页幅") Integer pageSize) {
        return articleService.getArticleByType(articleType,pageNo,pageSize);
    }

    @GetMapping("{id}")
    @ApiModelProperty("根据文章id查看内容")
    public BaseResponseBody<Article> articleDetail(
            @PathVariable @NotNull(message = "文章id不能为空") @ApiParam("文章id") String id) {
        return articleService.articleDetail(id);
    }

    @PostMapping("add")
    @ApiOperation("添加文章")
    @AdminRole
    public BaseResponseBody<Void> addArticle(@Validated @ApiParam("文章") @RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @GetMapping("translate")
    @ApiOperation("翻译")
    @AdminRole
    public BaseResponseBody<Map<Language, String>> translate(
            @RequestParam(required = false,defaultValue = "zh") @ApiParam("原文本语言类型") Language from,
            @RequestParam @ApiParam("待翻译文本") @NotEmpty(message = "待翻译文本不能为空") String message) {
        return articleService.translate(from,message);
    }
}
