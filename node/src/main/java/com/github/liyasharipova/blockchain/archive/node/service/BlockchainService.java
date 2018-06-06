package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.FutureBlock;
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

    //todo здесь будет цепочка блоков -- поле
    public void mineBlockAndPlaceToBlockchain(FutureBlock block) {
        mineBlock(block);
    }

    /**
     * todo Нужно замайнить блок, потом положить его в блокчейн
     *
     * @param block
     */
    private void mineBlock(FutureBlock block) {
    }
}