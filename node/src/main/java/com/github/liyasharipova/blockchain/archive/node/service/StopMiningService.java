package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.SuccessfulMinedByOthersBlocks;
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

    /**
     * Чтобы остановить майнинг в других циклах (потоках), кладется в список блок,
     * который успешно был замайнен другими нодами
     */
    public void stopMining(BlockDto miningInfoRequest) {
        SuccessfulMinedByOthersBlocks.getSuccessfulBlocks().add(miningInfoRequest);
    }
}