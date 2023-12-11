package com.example.tut05.controller;

import com.example.tut05.dto.UsersDto;
import com.example.tut05.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersMapper um;

    @GetMapping("")
    public String getUsers(Model model) {
        List<UsersDto> list = um.getUsers();
        model.addAttribute("userList", list); //값이 넘어간다
        return "users/list";
    }

    @GetMapping("/insert")
    public String getInsert() {
        return "users/insert";
    }

    @PostMapping("/insert")
    public String setInsert(@ModelAttribute UsersDto usersDto) {
        um.setInsert(usersDto);

        return "redirect:/users";
    }



}
