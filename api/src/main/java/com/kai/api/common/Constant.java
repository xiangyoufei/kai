package com.kai.api.common;

import com.kai.api.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {

    /**
     * 用户登录token缓存
     */
    private Map<String, User> tokenUserCache = new ConcurrentHashMap<>();

}
