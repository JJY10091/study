package com.example.tut03.controller;

import com.example.tut03.dto.RegDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class RegController {

    @GetMapping("/register")
    public String getReg() {
        return "users/register";
    }

    @PostMapping("/register")
    public void setReg(@ModelAttribute RegDto regDto) {
        System.out.println(regDto);
    }
}
