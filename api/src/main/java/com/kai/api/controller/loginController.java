package com.kai.api.controller;

import com.kai.api.common.BaseResponseBody;
import com.kai.api.model.User;
import com.kai.api.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {


    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResponseBody<String> login(@Validated @RequestBody User user){
        return loginService.login(user);
    }

}
