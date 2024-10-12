package com.hello.member.controller;

import com.hello.member.dto.MemberDTO;
import com.hello.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력 요청 메소드
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    /*
    public String save(@RequestParam(name = "memberEmail") String memberEmail,
        @RequestParam(name = "memberPassword") String memberPassword,
        @RequestParam(name = "memberName") String memberName) {
     */
    //@ModelAttribute: html에서 넘겨주는 name과 DTO의 필드명이 일치하다면 스프링이 객체의 setter를 호출해서 알아서 담아준다. (생략 가능)
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);

        memberService.save(memberDTO);

        return "index";
    }
}
