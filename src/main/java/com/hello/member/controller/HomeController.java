package com.hello.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //기본 페이지 요청 메소드
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
