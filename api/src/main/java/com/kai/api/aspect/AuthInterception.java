package com.kai.api.aspect;

import com.kai.api.common.Constant;
import com.kai.api.common.UserType;
import com.kai.api.common.exception.AuthenticateException;
import com.kai.api.common.exception.AuthorizeException;
import com.kai.api.model.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class AuthInterception {

    public static ThreadLocal<String> loginUser =new ThreadLocal<>();

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(com.kai.api.common.AdminRole)")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void checkAuth() {
//        final String token = request.getHeader("kai-token");
//        User user =null;
//        if (token == null || (user = Constant.tokenUserCache.get(token)) == null) {
//            throw new AuthenticateException("请先登录");
//        }
//
//        if (!(user.getUserType() == UserType.admin)) {
//            throw new AuthorizeException();
//        }
        User user =new User().setUsername("admin");
        loginUser.set(user.getUsername());
    }


}
