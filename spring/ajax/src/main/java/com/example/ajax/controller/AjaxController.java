package com.example.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.HashMap;

@Controller
public class AjaxController {

    //server test
    @GetMapping("/test")
    @ResponseBody
    public String getTest(){
        return "이 글자가 보이면 서버 설정 정상!!!";
    }

    @GetMapping("/getData")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> setData() {
        //email, passwd, name, age 보낼게
        Map<String, Object> map = new HashMap<>();
        map.put("email", "mail@mail.com");
        map.put("passwd", "1234");
        map.put("name","홍길동");
        map.put("age", 10);
        return map;
    }

    @GetMapping("/setData")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> setDate(String code, String name) {
        System.out.println(code);
        System.out.println(name);

        Map<String, Object> map = new HashMap<>();
        map.put("msg","success");

        return map;
    }
}
