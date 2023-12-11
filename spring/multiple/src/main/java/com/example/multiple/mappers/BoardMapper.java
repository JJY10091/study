package com.example.multiple.mappers;

import com.example.multiple.dto.BoardDto;
import com.example.multiple.dto.FileDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    //NULL이라면 1, 아니라면 grp최대값(MAX(grp))에 + 1
    @Select("SELECT IFNULL( MAX(grp) + 1 ,1 ) FROM board_${configCode}")
    public int getGrpMaxCnt(String configCode);

    //values안에 변수는 #, 그 외 $
    @Insert("INSERT INTO board_${configCode} VALUES(NULL, #{subject}, #{writer}, #{content}, 0, NOW(), #{grp}, 1, 1, #{isFiles});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void setBoard(BoardDto boardDto);

    @Insert("INSERT INTO files_${configCode} VALUES(#{id}, #{orgName}, #{savedFileName}, " +
            "#{savedPathName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFiles(FileDto fileDto);

    @Select("SELECT * FROM board_${configCode} ORDER BY id DESC")
    public List<BoardDto> getBoardList(String configCode);

    @Select("SELECT * FROM board_${configCode} WHERE id = #{id}")
    public BoardDto getBoard(String configCode, int id);

    @Select("SELECT * FROM files_${configCode} WHERE id = #{id}")
    public List<FileDto> getFiles(String configCode, int id);

}
