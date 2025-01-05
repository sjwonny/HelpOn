package com.example.helpon.mapper;

import com.example.helpon.dto.CertificateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CertificateMapper {
    public List<CertificateDto> getCertificate();

}
