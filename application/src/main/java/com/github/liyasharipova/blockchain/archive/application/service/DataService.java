package com.github.liyasharipova.blockchain.archive.application.service;

import com.github.liyasharipova.blockchain.archive.application.dto.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Сервис для работы с данным документа
 */
@Service
public class DataService {

    @Value("data.server.host")
    private String DATA_SERVER_HOST;

    @Value("data.server.port")
    private String DATA_SERVER_PORT;

    public List<FileDto> loadAllFiles() {
        RestTemplate restTemplate = new RestTemplate();

        return null;
    }

}