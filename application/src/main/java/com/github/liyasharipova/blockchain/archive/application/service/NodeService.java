package com.github.liyasharipova.blockchain.archive.application.service;

import com.github.liyasharipova.blockchain.archive.application.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с нодами
 */
@Service
@Slf4j
@Transactional
public class NodeService {

    @Value("node.host")
    private List<String> nodeHosts = new ArrayList<>();

    @Value("node.port")
    private List<String> nodePorts = new ArrayList<>();

    public void sendFileInfo(Transaction transaction) {
        RestTemplate restTemplate = new RestTemplate();

        // Отправка хэша каждой ноде
        for (int i = 0; i < nodeHosts.size(); i++) {
            String uri = "http://" + nodeHosts.get(i) + ":" + nodePorts.get(i) + "/receive-file-info";
            restTemplate.postForObject(uri, transaction, String.class);
            log.info("Отправлен хэш к серверу {}:{}", nodeHosts.get(i), nodePorts.get(i));

        }

        log.info("Отправлен хэш {} к {} серверам", transaction.getHash().substring(0, 6), nodeHosts.size());

    }
}