package com.example.md_exam.service;

import com.example.md_exam.dto.UserDto;
import com.example.md_exam.mappers.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public UserDto setLogin(UserDto userDto){
        return userMapper.setLogin(userDto);

    }

    public void setRegister(UserDto userDto){
        userMapper.setRegister(userDto);
    }
}
