package com.example.helpon.mapper;

import com.example.helpon.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    public void insert(FileDto fileDto);
    public void delete(Long boardNumber);
    public List<FileDto> selectList(Long boardNumber);
    public List<FileDto> selectOldList();
}
