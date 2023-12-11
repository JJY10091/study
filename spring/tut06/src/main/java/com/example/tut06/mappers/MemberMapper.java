package com.example.tut06.mappers;

import com.example.tut06.dto.MemberDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member VALUES(NULL, #{userid}, #{passwd})")
    public void setAdd(MemberDto mdto);

    @Select("SELECT * FROM member ORDER BY num DESC")
    public List<MemberDto> getMembers();

}
