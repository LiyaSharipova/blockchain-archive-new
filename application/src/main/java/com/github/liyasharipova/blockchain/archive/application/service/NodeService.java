package com.github.liyasharipova.blockchain.archive.application.service;

import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Сервис для работы с нодами
 */
@Service
@Slf4j
@Transactional
public class NodeService {

    @Value("#{'${node.hosts}'.split(',')}")
    private List<String> nodeHosts;

    @Value("#{'${node.ports}'.split(',')}")
    private List<String> nodePorts;

    public void sendFileInfo(TransactionDto transaction) {
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