package com.hello.member.controller;

import com.hello.member.dto.MemberDTO;
import com.hello.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null) {
            //로그인 성공
            //로그인한 사용자의 이메일 정보를 세션에 담음
            session.setAttribute("loginEmail", loginResult.getMemberEmail());

            return "main";
        } else {
            //로그인 실패
            return "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();

        //특정 html로 가져가야하는 데이터가 있다면 org.springframework.ui.Model 사용. (가져가는 방법 중 가장 기초적인 방식)
        model.addAttribute("memberList", memberDTOList);

        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);

        model.addAttribute("member", memberDTO);

        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        //로그인 정보가 세션에 있기 때문에 정보를 세션으로부터 가져온다.
        String myEmail = (String) session.getAttribute("loginEmail");

        MemberDTO memberDTO = memberService.updateForm(myEmail);

        model.addAttribute("updateMember", memberDTO);

        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);

        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);

        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam(name = "memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);

        String checkResult = memberService.emailCheck(memberEmail);

        if (checkResult != null) {
            return "ok";
        } else {
            return "no";
        }
    }
}
