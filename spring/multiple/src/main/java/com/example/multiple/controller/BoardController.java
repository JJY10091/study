package com.example.multiple.controller;

import com.example.multiple.dto.BoardDto;
import com.example.multiple.dto.FileDto;
import com.example.multiple.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
public class BoardController {

    @Value("${fileDir}")
    String fileDir;

    @Autowired
    BoardService boardService;

    @GetMapping("board/boardList")
    public String getBoardList(@RequestParam String configCode, Model model) {

        model.addAttribute("configCode",configCode);

        model.addAttribute("board",boardService.getBoardList(configCode));

        return "board/boardList";
    }

    @GetMapping("board/boardWrite")
    public String getBoardWrite(@RequestParam String configCode, Model model) {
        model.addAttribute("configCode",configCode); // html에 자료 보내주는
        return "board/boardWrite";
    }

//    forward : /board/list?page=1&date=123
//    redirect : 뒤에 데이터없이

    @PostMapping("board/boardWrite")
    public String setBoardWrite(
            @RequestParam("files") List<MultipartFile> files,
            @ModelAttribute BoardDto boardDto,
                                     Model model) throws IOException {

        int grp = boardService.getGrpMaxCnt(boardDto.getConfigCode());
        boardDto.setGrp(grp);



        if( !files.get(0).isEmpty() ){
            boardDto.setIsFiles("Y");
            boardService.setBoard(boardDto);
            int fileId = boardDto.getId();

            //20231207
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

            File makeFolder = new File(fileDir + folderName);
            if( !makeFolder.exists() ) {
                makeFolder.mkdir();
            }

            FileDto fileDto = new FileDto();

            for(MultipartFile mf : files) {
                //경로명 + uuid
                String savedPathName = fileDir + folderName;

                String orgName = mf.getOriginalFilename();
                String ext = orgName.substring(orgName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + ext;

                mf.transferTo(new File(savedPathName + "/" + savedFileName)); //파일 업로드하는거

                fileDto.setConfigCode(boardDto.getConfigCode());
                fileDto.setId(fileId);
                fileDto.setOrgName(orgName);
                fileDto.setSavedFileName(savedFileName);
                fileDto.setSavedPathName(savedPathName);
                fileDto.setSavedFileSize(mf.getSize());
                fileDto.setFolderName(folderName);
                fileDto.setExt(ext);

                boardService.setFiles(fileDto);
            }

        }else {
            boardService.setBoard(boardDto);
        }

       /* if( boardDto != null ){
            int grp = boardService.getGrpMaxCnt(boardDto.getConfigCode());
            boardDto.setGrp(grp);
            boardService.setBoard(boardDto);
        }
        model.addAttribute("configCode",boardDto.getConfigCode()); // html에 자료 보내주는 */

        return "redirect:/board/boardList?configCode="+boardDto.getConfigCode();
    }

    @GetMapping("/board/boardView")
    public String getBoardView(@RequestParam String configCode, @RequestParam int id, Model model) {

        model.addAttribute("board",boardService.getBoard(configCode,id));
        model.addAttribute("files",boardService.getFiles(configCode, id));
        return "/board/boardView";
    }


}
