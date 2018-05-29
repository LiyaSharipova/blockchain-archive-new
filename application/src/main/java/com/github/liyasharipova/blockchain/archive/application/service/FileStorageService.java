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

import java.io.IOException;
import java.util.List;

/**
 * Сервис для работы с данным документа
 */
@Service
public class FileStorageService {

    @Value("data.server.host")
    private String DATA_SERVER_HOST;

    @Value("data.server.port")
    private String DATA_SERVER_PORT;

    private String FILE_STORAGE_URL;

    public FileStorageService() {
        this.FILE_STORAGE_URL = "http://" + DATA_SERVER_HOST + ":" + DATA_SERVER_PORT;
    }

    public List<FileDto> getAllFiles() {
        RestTemplate restTemplate = new RestTemplate();

        String uri = FILE_STORAGE_URL + "/";

        ResponseEntity<List<FileDto>> rateResponse =
                restTemplate.exchange(uri,
                                      HttpMethod.GET,
                                      null,
                                      new ParameterizedTypeReference<List<FileDto>>() {
                                      });

        return rateResponse.getBody();
    }

    public Resource getFile(String fileHash) {
        RestTemplate restTemplate = new RestTemplate();

        String url = FILE_STORAGE_URL + "/" + fileHash;

        return restTemplate.getForObject(url, Resource.class);
    }

    public void uploadFile(MultipartFile file) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = FILE_STORAGE_URL + "/upload-file";

        try {
            ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes());
            FileDto fileDto = new FileDto(file.getOriginalFilename(), "hash", byteArrayResource);
            restTemplate.postForObject(uri, fileDto, FileDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}