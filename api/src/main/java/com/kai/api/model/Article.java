package com.kai.api.model;

import com.kai.api.common.ArticleType;
import com.kai.api.common.Language;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel("文章")
@Table(name = "article")
@Entity
@Persistent
public class Article {

    @ApiModelProperty("文章id")
    @Id
    private String id;

    @ApiModelProperty("标题")
    @NotEmpty(message = "文章标题不能为空")
    private String title;

    @ApiModelProperty("正文")
    @NotEmpty(message = "文章正文不能为空")
    @Lob
    @Column(columnDefinition="TEXT")
    private String content;

    @ApiModelProperty("摘要")
    @NotEmpty(message = "文章摘要不能为空")
    private String summary;

    @ApiModelProperty("图片")
    @NotNull(message = "文章图片不能为空")
    @Lob
    @Column(columnDefinition="TEXT")
    private String images;

    @ApiModelProperty("类型")
    @NotNull(message = "文章类型不能为空")
    @Enumerated(EnumType.STRING)
    private ArticleType articleType;

    @ApiModelProperty("语言")
    @NotNull(message = "文章语言类型不能为空")
    @Enumerated(EnumType.STRING)
    private Language language;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("创作时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改者")
    private String updater;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
