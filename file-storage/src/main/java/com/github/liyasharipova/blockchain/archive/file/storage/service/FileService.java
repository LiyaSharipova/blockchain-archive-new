package com.github.liyasharipova.blockchain.archive.file.storage.service;

import com.github.liyasharipova.blockchain.archive.file.storage.dto.FileDto;
import com.github.liyasharipova.blockchain.archive.file.storage.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
}