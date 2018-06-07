package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Сервис для работы с блоком
 */
@Service
@Slf4j
@Transactional
public class BlockService {

    /** Вычисляем новый хэш на основе содержимого блока */
    public String calculateHash(BlockDto blockDto) {
        return StringUtil.applyStribog(
                blockDto.getPreviousHash() +
                        Long.toString(blockDto.getTimeStamp()) +
                        Long.toString(blockDto.getNonce()) +
                        blockDto.getMerkleRoot()
        );
    }

}