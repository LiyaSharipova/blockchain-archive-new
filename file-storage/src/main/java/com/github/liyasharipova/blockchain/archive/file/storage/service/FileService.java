package com.github.liyasharipova.blockchain.archive.file.storage.service;

import com.github.liyasharipova.blockchain.archive.file.storage.entity.FileEntity;
import com.github.liyasharipova.blockchain.archive.file.storage.repository.FileStorageRepository;
import com.github.liyasharipova.blockchain.filestorage.api.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class FileService {

    @Autowired
    private FileStorageRepository fileStorageRepository;

    public List<FileDto> getAllFiles() {
        List<FileDto> files = new ArrayList<>();

        fileStorageRepository.findAll().forEach(fileEntity -> {
            FileDto fileDto = new FileDto();
            fileDto.setId(fileEntity.getId());
            fileDto.setName(fileEntity.getName());
            fileDto.setHash(fileEntity.getHash());
            files.add(fileDto);
        });

        return files;

    }

    public Long saveFile(FileDto fileDto) {
        FileEntity entity = null;
        FileEntity fileEntity = new FileEntity(fileDto.getName(), fileDto.getHash(), fileDto.getData(), fileDto.getBlockNumber());
        entity = fileStorageRepository.save(fileEntity);
        return entity.getId();
    }

    public Resource getFileById(Long id) {
        byte[] data = fileStorageRepository.getFileEntityById(id).getData();
        return new ByteArrayResource(data);
    }
}