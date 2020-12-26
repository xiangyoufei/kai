package com.kai.api.service;

import com.kai.api.common.BaseResponseBody;
import com.kai.api.model.User;

public interface LoginService {


    /**
     * 用户登录
     * @param user
     * @return
     */
    BaseResponseBody<String> login(User user);

}
