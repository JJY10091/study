package com.example.upload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {
    @Value("${fileDir}")
    String fileDir;


    @GetMapping("/upload")
    public String getUploda() {
        return "upload/upload";
    }

    @GetMapping("/view")
    public String getView() {
        return "upload/view";
    }
    @PostMapping("/upload")
    public String setUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        //업로드
        //System.out.println(file.getOriginalFilename());
        //System.out.println(file.getSize() / 1000 + "kb");

        //파일 이름 중복 -> 변경(uuid) -> 확장자 기준으로 배열 0번 -> uuid 변경하여 -> 저장
        //UUID 임의의 난수 생성

        //String fileDir = "C:/Users/ITPS/Downloads/upload/src/main/resources/static/upload/";

        String origName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String ext = origName.substring( origName.lastIndexOf(".") );
        String fileName = uuid + ext;

        //저장주소, 파일이름이 필요함
        String savePathFile = fileDir + fileName;
        file.transferTo(new File(savePathFile)); //저장 완료

        //view에 이미지 보내기 : model사용
        //경로는 application.properties에 설정한 상대경로기반으로 파일을 가져옴
        model.addAttribute("filename", fileName);

        return "upload/view";
    }

}
