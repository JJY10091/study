package com.example.crud.controller;

import com.example.crud.dto.ItemsDto;
import com.example.crud.mappers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ItemMapper im;

    @GetMapping("/addItem")
    public String getAddItem() {
        return "admin/addItem";
    }

    @PostMapping("/addItem")
    @ResponseBody
    public Map<String, Object> setAddItem(@ModelAttribute ItemsDto idto) {
        Map<String, Object> map = new HashMap<>();
        if( idto != null) {
            //쿼리 실행
            im.setAddItem(idto);

            //html 통신
            map.put("message", "success");
        }else {
            map.put("message", "failure");
        }
        return map;
    }

    @GetMapping("/deleteItem")
    @ResponseBody
    public Map<String, Object> deleteItem(@RequestParam int id) {
        Map<String, Object> map = new HashMap<>();

        if( id > 0 ){
            im.deleteItem(id);
            map.put("message", "success");

        }else{
            map.put("message", "failure");
        }
        return map;
    }

    @GetMapping("/updateItem")
    public String getUpdateItem(@RequestParam int id, Model model) {

        ItemsDto result = im.viewItem(id);
        model.addAttribute("item",result);
        return "admin/updateItem";
    }

    @PostMapping("/updateItem")
    @ResponseBody
    public  Map<String, Object> setUpdateItem(@ModelAttribute ItemsDto itemsDto) {

        Map<String, Object> map = new HashMap<>();
        if( itemsDto != null ) {
            im.setUpdateItem(itemsDto);
            map.put("message", "success");
        }else{
            map.put("message", "failure");
        }
        return map;
    }
}
