package org.example.sa.rbac.demo.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler(Exception.class)
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error("系统繁忙，请稍候重试！");
    }

    @ExceptionHandler(NotLoginException.class)
    public SaResult handleNotLogin(SaTokenException e) {
        SaResult saResult = new SaResult();
        saResult.setCode(401);
        saResult.setMsg(e.getMessage());
        return saResult;
    }
}
