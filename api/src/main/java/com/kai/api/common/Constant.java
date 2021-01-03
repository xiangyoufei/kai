package com.kai.api.common;

import com.kai.api.model.User;
import com.kai.api.util.ExpiryMap;

public class Constant {

    /**
     * 用户登录token缓存
     */
    public static ExpiryMap<String, User> tokenUserCache = new ExpiryMap<>();

    public static final String BAIDU_SUCCESS_CODE="52000";

}
