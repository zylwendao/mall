package com.customer.web;

import com.customer.entity.TDncCustomer;
import com.customer.service.TestService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "test")
@CrossOrigin
public class TestController {

    @Resource
    TestService testService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("1")
    public String test1(Model model) {
        List<TDncCustomer> users = testService.testServiceMethod();
        model.addAttribute("users",users);
        model.addAttribute("aaa","1122");
        return "/aaa";
    }
    @RequestMapping("2")
    public String test2(Model model) {
        List<TDncCustomer> users = testService.testServiceMethod();
        model.addAttribute("users",users);
        return "/bbb";
    }
    @RequestMapping(value = "6")
    @ResponseBody
    public String start6(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.setContentType("text/json; charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        System.out.println(request.getParameter("key"));
        String ukey_pubKey = stringRedisTemplate.opsForValue().get("Ukey_PubKey");
        return "a,aa,aaa,b,bb,bbb";
    }
}