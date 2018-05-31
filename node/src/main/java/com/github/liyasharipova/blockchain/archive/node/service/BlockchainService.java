package com.github.liyasharipova.blockchain.archive.node.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Сервис для работы с блокчейном
 */
@Service
@Slf4j
@Transactional
public class BlockchainService {

    public void putHashIntoTransaction(String hash) {
        // Положить хэш в транзакцию, потом проследить, нужно ли замайнить блок (если блок заполнен)
        // Если нужно замайнить блок, то запрашивается nonceRange у application server
    }
}