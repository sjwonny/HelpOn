package com.example.helpon.controller;

import com.example.helpon.dto.FileDto;
import com.example.helpon.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/*")
public class FileController {
    private final FileService fileService;

    @Value("C:/upload/")
    private String fileDir;

    @GetMapping("/imgList")
    public List<FileDto> imgList(Long boardNumber){ return fileService.findList(boardNumber);}

    //서버 컴퓨터에 저장된 파일을 복사하여 넘겨주는 핸들러
    @GetMapping("/display")//실제 이미지를 띄우는 것
    public byte[] display(String fileName) throws IOException{
        return FileCopyUtils.copyToByteArray(new File(fileDir,fileName));
    }

    @GetMapping("/download")
    //HttpServletResponse 와 동일하게 ResponseEntity 객체는 응답을 나타내는 객체이다.
    // 스프링에서 지원하는 응답객체이며 기존의 응답 객체보다 간편하게 설정할 수 있다는 장점이 있다.
    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException{
        //Resource 객체는 자원을 나타내는 객체이다. -> 스프링에서 지원하는 타입
        //우리는 파일이라는 리소스를 다운로드 처리하기 위해 사용하고 있으며 File객체보다
        //많은 종류의 리소스를 다룰 수 있고 스프링과 호환성이 좋다.
        //Resource는 인터페이스이므로 객체를 만들 때는 자식클래스를 사용한다.

        //JSP에서 File 객체를 사용하여 다운로드를 구현했던 것을 스프링에서 지원하는 Resource객체로 구현한다.
        Resource resource = new FileSystemResource(fileDir + fileName);
        HttpHeaders headers = new HttpHeaders();
        String name = resource.getFilename();

        //uuid 를 제거하고 다운받도록 한다.
        //indexOf("타겟") : 타겟이 몇 번째 인덱스인지 반환해준다.
        //aaa_bbb : 3
        //substring(index) : 문자열에서 해당 인덱스 번호까지만 잘라준다.
        name = name.substring(name.indexOf("_") + 1);

        headers.add("Content-Dispostion", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK); //헤더 설정이유 :response를 보냈을 떄 이 response가 화면을 다운로드
        //하는 애인지 화면에 뿌려주는 애인지 json으로 받아서 처리해야하는건지 헤더에 정보가 있기 떄문.
        // 그 정보를 Content-Dispositon에 받아 넣어줌. attachment를 쓰면 다운로드가 됨.
    }
}
