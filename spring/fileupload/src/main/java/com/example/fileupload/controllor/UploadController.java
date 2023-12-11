package com.example.fileupload.controllor;

import org.apache.tomcat.util.buf.UriUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
public class UploadController {

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/upload")
    public String getUpload() {
        return "upload/uploadForm";
    }

    @PostMapping("/upload")
    public String setUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        //저장 폴더 생성(날짜를 이용해서 생성)
        //20231124
        //String이 아닌 File 객체를 사용해야하네
        String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        File makeFolder = new File(fileDir + folderName);

        if( !makeFolder.exists() ){
            makeFolder.mkdir();
        }

        String oriName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String ext = oriName.substring(oriName.lastIndexOf("."));

        String savedFileName = uuid + ext; //이미지 표시할때
        String savedPathFileName = fileDir + folderName + "/" + savedFileName; //다운로드할때 필요한 경로 + 파일이름

        //파일 쓰기
        file.transferTo(new File(savedPathFileName));

        model.addAttribute("savedFileName",savedFileName);
        model.addAttribute("folderName",folderName);
        model.addAttribute("savedPathFileName",savedPathFileName);

        return "upload/uploadView";
    }

    @GetMapping("/uploadView")
    public String getUploadView() {
        return "upload/uploadView";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getDownload(@RequestParam String savedPathFileName) throws MalformedURLException {

        UrlResource resource = new UrlResource("file:" + savedPathFileName);
        String encodeFileName = UriUtils.encode(savedPathFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" +encodeFileName+ "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }


}
