package com.github.liyasharipova.blockchain.archive.file.storage.service;

import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.archive.file.storage.entity.FileEntity;
import com.github.liyasharipova.blockchain.archive.file.storage.repository.FileStorageRepository;
import com.github.liyasharipova.blockchain.filestorage.api.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            Optional.ofNullable(fileEntity.getBlockNumber())
                    .ifPresent(fileDto::setBlockNumber);
            files.add(fileDto);
        });

        return files;

    }

    public Long saveFile(FileDto fileDto) {
        FileEntity entity = null;
        FileEntity fileEntity =
                new FileEntity(fileDto.getName(), fileDto.getHash(), fileDto.getData(), fileDto.getBlockNumber());
        entity = fileStorageRepository.save(fileEntity);
        return entity.getId();
    }

    public AbstractMap.SimpleEntry<String, ByteArrayResource> getFileById(Long id) {
        FileEntity entity = fileStorageRepository.getFileEntityById(id);
        byte[] data = entity.getData();
        return new AbstractMap.SimpleEntry<>(entity.getName(), new ByteArrayResource(data));

    }

    public void setBlocks(BlockRequest blockRequest) {
        blockRequest.getFileIds().forEach(fileId -> {
            FileEntity fileEntity = fileStorageRepository.findOne(fileId);
            fileEntity.setBlockNumber(blockRequest.getBlockNumber());
            fileStorageRepository.save(fileEntity);
        });
    }
}