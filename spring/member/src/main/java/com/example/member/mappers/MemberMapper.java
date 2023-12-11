package com.example.member.mappers;

import com.example.member.dto.MemberDto;
import org.apache.ibatis.annotations.*;

import java.lang.reflect.Member;
import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member VALUES(NULL, #{userid}, #{passwd}, #{username}, NOW())")
    void setInert(MemberDto memberDto);

    @Select("SELECT *FROM member ${queryString} ORDER BY id DESC")
    List<MemberDto> getMemberList(String queryString);

    @Select("SELECT COUNT(*) FROM member ${queryString}")
    int getMemberCount(String queryString);

    @Delete("DELETE FROM member WHERE id = ${id}")
    void deleteMember(int id);

    @Select("SELECT * FROM member WHERE id = ${id}")
    MemberDto viewMember(int id);

    @Select("SELECT * FROM member WHERE userid = #{userid} AND passwd = #{passwd}")
    MemberDto setLogin(MemberDto memberDto);

    @Update("UPDATE member SET userid = #{userid}, passwd = #{passwd}, username = #{username}, regdate = now() WHERE id = #{id}")
    void setUpdate(MemberDto memberDto);

}
