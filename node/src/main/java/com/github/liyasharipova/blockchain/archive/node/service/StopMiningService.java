package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlocksQueue;
import com.github.liyasharipova.blockchain.node.api.dto.request.MiningInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 */
@Service
@Transactional
@Slf4j
public class StopMiningService {

    public Object stopMining(MiningInfoRequest miningInfoRequest) {
        return null;
    }
}