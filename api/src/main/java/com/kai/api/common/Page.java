package com.kai.api.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
@Accessors(chain = true)
public class Page<T> implements Serializable {
	@ApiModelProperty(value = "记录总数",position = 1)
	private long total;

	@ApiModelProperty(value = "第几页",position = 2)
	private int pages;

	@ApiModelProperty(value = "查询结果",position = 3)
	private List<T> data;
}
