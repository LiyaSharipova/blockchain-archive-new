package com.github.liyasharipova.blockchain.archive.application.service;

import com.github.liyasharipova.blockchain.archive.application.dto.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
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
public class FileStorageService {

    @Value("${file.storage.server.host}")
    private String FILE_STORAGE_SERVER_HOST;

    @Value("${file.storage.server.port}")
    private String FILE_STORAGE_SERVER_PORT;

    private String FILE_STORAGE_URL;

    @PostConstruct
    public void init(){
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

    public Long uploadFile(MultipartFile file, String hash) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = FILE_STORAGE_URL + "/upload-file";

        try {
            ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes());
            FileDto fileDto = new FileDto(file.getOriginalFilename(), hash, byteArrayResource);
            restTemplate.postForObject(uri, fileDto, FileDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
//        todo return fileId
        return null;
    }
}