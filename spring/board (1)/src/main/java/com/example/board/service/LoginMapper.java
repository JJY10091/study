package com.example.board.service;

import com.example.board.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT * FROM member WHERE userid = #{userid} AND passwd = #{passwd}")
    MemberDto setLogin(MemberDto memberDto);

}
