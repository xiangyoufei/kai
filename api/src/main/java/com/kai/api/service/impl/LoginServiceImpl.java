package com.kai.api.service.impl;

import com.kai.api.common.BaseResponseBody;
import com.kai.api.common.Constant;
import com.kai.api.common.exception.AuthenticateException;
import com.kai.api.model.User;
import com.kai.api.repository.UserRepository;
import com.kai.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository repository;

    @Override
    public BaseResponseBody<String> login(User user) {
        final Optional<User> optional = repository.findOne(Example.of(user));
        if (optional.orElse(null) == null) {
            throw new AuthenticateException();
        }
        String token = UUID.randomUUID().toString();
        Constant.tokenUserCache.put(token, user);
        return new BaseResponseBody<String>().setData(token).setMessage("登录成功");
    }
}
