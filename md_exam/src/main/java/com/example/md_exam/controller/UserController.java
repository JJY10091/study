package com.example.md_exam.controller;

import com.example.md_exam.dto.UserDto;
import com.example.md_exam.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLogin(){
        return "/login";
    }
    @GetMapping("/register")
    public String getRegister(){
        return "/register";
    }
    @PostMapping("/register")
    public String setRegister(@ModelAttribute UserDto userDto, RedirectAttributes ra){
        userService.setRegister(userDto);
        ra.addFlashAttribute("msg" , "success");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String setLogin(@ModelAttribute UserDto userDto, RedirectAttributes ra, HttpServletRequest hsr){

        UserDto d = userService.setLogin(userDto);

        if(d != null){
            //세션 생성 - 로그아웃하기전까지 계속 로그인유지
            //getSession() -> 데이터 -> 시간

            HttpSession hs = hsr.getSession(); //세션 준비

            hs.setAttribute("user",d);
            hs.setMaxInactiveInterval(15*60);   //10분
            ra.addFlashAttribute("userName",d.getUserName());
            return "redirect:/index";

        }else{
            ra.addFlashAttribute("message","아이디/비밀번호를 확인하세요");
            return "redirect:/login";
        }
    }
    @GetMapping("/logout")
    public String getLogout(HttpSession hs) {
        System.out.println("logout");
        hs.invalidate();
        return "redirect:/index";
    }
}
