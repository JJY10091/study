package com.example.board.controller;

import com.example.board.dto.MemberDto;
import com.example.board.service.LoginMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    LoginMapper loginMapper;

    @GetMapping("")
    public String getHome() {
        return "home/index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "home/login";
    }

    @PostMapping("/login")
    public String setLogin(@ModelAttribute MemberDto memberDto,
                           HttpServletRequest req) {
        MemberDto m = loginMapper.setLogin(memberDto);
        if( m != null) {
            HttpSession hs = req.getSession();
            hs.setAttribute("LoginInfo", m);
            hs.setMaxInactiveInterval(60 * 10);
        }
        return "redirect:/board/list";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession hs) {
        hs.invalidate();
        return "redirect:/login";
    }

}
