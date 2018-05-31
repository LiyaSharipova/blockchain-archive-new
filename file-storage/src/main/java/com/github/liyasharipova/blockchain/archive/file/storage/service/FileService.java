package com.github.liyasharipova.blockchain.archive.file.storage.service;

import com.github.liyasharipova.blockchain.archive.file.storage.dto.FileDto;
import com.github.liyasharipova.blockchain.archive.file.storage.entity.FileEntity;
import com.github.liyasharipova.blockchain.archive.file.storage.repository.FileStorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
//todo InterceptorBinding
@Transactional
public class FileService {

    @Autowired
    private FileStorageRepository fileStorageRepository;

    public Resource getFileByHash(String fileHash) {
        byte[] data = fileStorageRepository.getFileByHash(fileHash).getData();
        return new ByteArrayResource(data);
    }

    public List<FileDto> getAllFiles() {
        List<FileDto> files = new ArrayList<>();

        fileStorageRepository.findAll().forEach(fileEntity -> {
            FileDto fileDto = new FileDto();
            fileDto.setName(fileEntity.getName());
            fileDto.setHash(fileEntity.getHash());
            files.add(fileDto);
        });

        return files;

    }

    public void saveFile(MultipartFile file, String hash) {
        try {
            FileEntity fileEntity = new FileEntity(file.getOriginalFilename(), hash, file.getBytes());
            fileStorageRepository.save(fileEntity);
        } catch (IOException e) {
            log.error(e.getMessage(), e.getCause());
        }
    }
}