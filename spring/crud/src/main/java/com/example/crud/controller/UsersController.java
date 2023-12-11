package com.example.crud.controller;

import com.example.crud.dto.ItemsDto;
import com.example.crud.mappers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    ItemMapper im;

    @GetMapping("")
    public String getListItem(){
        return "users/listItem";
    }

    @PostMapping("")
    @ResponseBody
    public Map<String, Object> getListInfo() {

        Map<String, Object> map = new HashMap<>();

        List<ItemsDto> list = im.getListItem();
        map.put("items",list);

        int res = im.getCount();
        map.put("count",res);

        return map;
    }

    @GetMapping("/viewItem")
    public String viewItem(@RequestParam int id, Model model) {
        ItemsDto result = im.viewItem(id);

        //json(ajax) 말고 thymleaf를 사용해서 보내볼게
        model.addAttribute("item", result); // 이것(model)만쓰면 html에 자료를 보내줌

        return "users/viewItem";
    }
}
