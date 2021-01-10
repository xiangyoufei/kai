package com.kai.api.model;

import com.kai.api.common.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
@ApiModel("用户")
@Table(name="user")
@Persistent
@Entity
public class User {

    @ApiModelProperty("id")
    @Id
    private String id;

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty("用户类型")
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
