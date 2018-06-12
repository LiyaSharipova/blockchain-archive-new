package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.BlocksQueue;
import com.github.liyasharipova.blockchain.node.api.dto.request.NonceCheckRequest;
import com.github.liyasharipova.blockchain.node.api.dto.response.NonceCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 */
@Service
@Transactional
@Slf4j
public class MiningResultCheckerService {

    private BlockService blockService;

    @Autowired
    public MiningResultCheckerService(
            BlockService blockService) {
        this.blockService = blockService;
    }

    public NonceCheckResponse checkMinedBlockInfo(NonceCheckRequest nonceCheckRequest) {
        BlockDto lastBlock = BlocksQueue.getBlocksQueue().peek();

        Long possibleCorrectNonce = nonceCheckRequest.getNonce();
        //todo создать новый блок, чтобы ... что состояние обновленного blockDto не будет конфликтовать с другими сервисами,
        // так как он используется в разных местах
        lastBlock.setNonce(possibleCorrectNonce);
        String calculatedHash = blockService.calculateHash(lastBlock);
        // Если не совпали, то просим остальные ноды и себя запустить selfcheck()
        if (!nonceCheckRequest.getBlockHash().equals(calculatedHash)) {

        } else {
            //А если все нормально, остановить майнинг и добавить в блокчейн замайненный блок

        }
        //todo проверить, что транзакции те же самые -- но зачем, пока не ясно

        return null;
    }
}