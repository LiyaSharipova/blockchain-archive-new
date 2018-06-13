package com.github.liyasharipova.blockchain.archive.application.service;

import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.filestorage.api.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

/**
 * Сервис для работы с данным документа
 */
@Service
@Slf4j
public class FileStorageService {

    @Value("${file.storage.server.host}")
    private String FILE_STORAGE_SERVER_HOST;

    @Value("${file.storage.server.port}")
    private String FILE_STORAGE_SERVER_PORT;

    private String FILE_STORAGE_URL;

    @PostConstruct
    public void init() {
        this.FILE_STORAGE_URL = "http://" + FILE_STORAGE_SERVER_HOST + ":" + FILE_STORAGE_SERVER_PORT;
    }

    public List<FileDto> getAllFiles() {
        RestTemplate restTemplate = new RestTemplate();

        String uri = FILE_STORAGE_URL + "/files";

        ResponseEntity<List<FileDto>> fileDtoEntity =
                restTemplate.exchange(uri,
                                      HttpMethod.GET,
                                      null,
                                      new ParameterizedTypeReference<List<FileDto>>() {
                                      });

        return fileDtoEntity.getBody();
    }

    public Resource getFile(@NotBlank Long fileId) {
        RestTemplate restTemplate = new RestTemplate();

        String url = FILE_STORAGE_URL + "/files/" + fileId;

        return restTemplate.getForObject(url, Resource.class);
    }

    public Long uploadFile(MultipartFile file, String hash) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String uri = FILE_STORAGE_URL + "/upload-file";

        Long fileId = null;
        try {
            FileDto fileDto = new FileDto(file.getOriginalFilename(), hash, file.getBytes());
            fileId = restTemplate.postForObject(uri, fileDto, Long.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return fileId;
    }

    public void sendBlock(BlockRequest blockRequest) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(FILE_STORAGE_URL + "/set-blocks", blockRequest, Void.class);
    }
}