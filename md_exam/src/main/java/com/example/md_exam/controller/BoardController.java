package com.example.md_exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping()
    public String getBoard(){
        return "board/board";
    }
    @GetMapping("/boardView")
    public String getBoardView(){
        return "board/boardView";
    }
    @GetMapping("/boardWeb")
    public String getBoardWeb(){
        return "board/boardWeb";
    }
    @GetMapping("/boardWrite")
    public String getBoardWrite(){
        return "board/boardWrite";
    }
}
