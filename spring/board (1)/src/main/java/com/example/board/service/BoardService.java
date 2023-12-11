package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageDto;
import com.example.board.mappers.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<BoardDto> getSearch(int page, String searchType, String words) {

        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
        if( searchType.equals("subject") || searchType.equals("writer") ) {
            searchQuery = "WHERE " + searchType + " = '" + words + "'";

        }else if( searchType.equals("content") ) {
            searchQuery = "WHERE " + searchType + " LIKE '%"+words+"%'";

        }else {
            searchQuery = "";
        }

        PageDto pageDto = new PageDto();
        //limit 시작, 개수

        int startNum = (page - 1) * pageDto.getPageCount() ;

        map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());


        return boardMapper.getList(map);

    }

    public int getSearchCnt(String searchType, String words) {

        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
        if( searchType.equals("subject") || searchType.equals("writer") ) {
            searchQuery = "WHERE " + searchType + " = '" + words + "'";

        }else if( searchType.equals("content") ) {
            searchQuery = "WHERE " + searchType + " LIKE '%"+words+"%'";

        }else {
            searchQuery = "";
        }

        map.put("searchQuery", searchQuery);
        return boardMapper.getListCount(map);

    }

    public void setDelete(int id) {
        if( id > 0 ) {
            BoardDto bd = boardMapper.getView(id);
            boardMapper.setDelete(id);

            String removeFile = bd.getSavedFilePathName();
            if( removeFile != null ) {
                File f = new File(removeFile);
                if( f.exists() ) {
                    f.delete();
                }
            }
        }
    }

    /* 페이지 알고리즘 구현 */
    public PageDto BoardPageCalc(int page) {
        PageDto pageDto = new PageDto();

        int totalCount = boardMapper.totalCount();
        int totalPage = (int)Math.ceil((double) totalCount / pageDto.getPageCount());
        int startPage
                = ((int) (Math.ceil((double) page / pageDto.getBlockCount())) - 1) * pageDto.getBlockCount() + 1;

        int endPage = startPage + pageDto.getBlockCount() - 1;

        if( endPage > totalPage ) {
            endPage = totalPage;
        }

        pageDto.setPage(page);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setTotalPage(totalPage);

        return pageDto;
    }


    /* 계산한 페이지 값을 select 보내서 limit */
}
