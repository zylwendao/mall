package com.customer.controller;

import com.customer.config.LoginInterceptor;
import com.customer.entity.YdlLoginUser;
import com.customer.entity.YdlUser;
import com.customer.service.YdlUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "index")
@CrossOrigin
@Slf4j
public class IndexController {

    @Resource
    YdlUserService ydlUserService;
    @Resource
    LoginInterceptor loginInterceptor;
    @PostMapping("login")
    public ResponseEntity<YdlLoginUser> login(@RequestBody @Validated YdlUser ydlUser, BindingResult bindingResult) throws Exception {
        //处理不合法数据
        bindingResult.getAllErrors().forEach(error -> log.error("登录校验失败", error.getDefaultMessage()));
        if (bindingResult.getAllErrors().size() > 0) {
            return ResponseEntity.status(500).build();
        }
        //执行登录
        YdlLoginUser ydlLoginUser = ydlUserService.login(ydlUser.getUserName(), ydlUser.getPassword());
        return ResponseEntity.ok().body(ydlLoginUser);
    }

}