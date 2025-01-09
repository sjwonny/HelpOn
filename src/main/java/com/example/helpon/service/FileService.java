package com.example.helpon.service;

import com.example.helpon.dto.Profile_fileDto;
import com.example.helpon.mapper.ProfileFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnailator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private ProfileFileMapper profileFileMapper;

    @Value("C:/upload/")
    private String fileDir;

    public void registerProfile(Profile_fileDto profile_fileDto) {
       if(profile_fileDto ==null) {throw new IllegalArgumentException("프로필 파일 누락"); }
       profileFileMapper.insert(profile_fileDto);
    }

    //파일 저장처리
    public Profile_fileDto saveProfile(MultipartFile file) throws IOException {
        String originName = file.getOriginalFilename();
        originName = originName.replaceAll("\\s+", "");

        UUID uuid = UUID.randomUUID();

        String sysName = uuid.toString() + "_" + originName;

        File uploadPath = new File(fileDir, getUploadPath());

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        File uploadFile = new File(uploadPath, sysName);

        file.transferTo(uploadFile);

        if (Files.probeContentType(uploadFile.toPath()).startsWith("image")) {
            FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_" + sysName));
            Thumbnailator.createThumbnail(file.getInputStream(), out, 300, 200);
            out.close();
        }

        Profile_fileDto profile_fileDto = new Profile_fileDto();
        profile_fileDto.setProfileFileUuid(uuid.toString());
        profile_fileDto.setProfileFileName(originName);
        profile_fileDto.setProfileFileUploadPath(getUploadPath());

        return profile_fileDto;
    }

    private String getUploadPath() {return new SimpleDateFormat("yyyy/MM/dd").format(new Date());}


    public void registerAndSaveFile(MultipartFile file, Long userNumber)throws IOException {
        Profile_fileDto profile_fileDto = saveProfile(file);
        profile_fileDto.setUserNumber(userNumber);
        registerProfile(profile_fileDto);
    }
}
