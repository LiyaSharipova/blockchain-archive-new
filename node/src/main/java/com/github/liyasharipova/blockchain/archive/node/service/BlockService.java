package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.SuccessfulMinedByOthersBlocks;
import com.github.liyasharipova.blockchain.archive.node.entity.BlockEntity;
import com.github.liyasharipova.blockchain.archive.node.entity.TransactionEntity;
import com.github.liyasharipova.blockchain.archive.node.repository.BlockRepository;
import com.github.liyasharipova.blockchain.archive.node.util.StringUtil;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;

/**
 * Сервис для работы с блоком
 */
@Service
@Slf4j
@Transactional
public class BlockService {

    @Autowired
    BlockRepository blockRepository;

    /**
     * Вычисляем новый хэш на основе содержимого блока
     */
    public String calculateHash(BlockDto blockDto) {
        return StringUtil.applyStribog(
                blockDto.getPreviousHash() +
                        Long.toString(blockDto.getNonce()) +
                        blockDto.getMerkleRoot()
        );
    }

    public void saveBlock(BlockDto blockDto) {
        BlockEntity blockEntity = new BlockEntity();
        blockEntity.setHash(blockDto.getHash());
        blockEntity.setNonce(blockDto.getNonce());
        blockEntity.setPreviousHash(blockDto.getPreviousHash());
        blockDto.getTransactions().forEach(transactionDto -> {
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setFileHash(transactionDto.getHash());
            transactionEntity.setUploadedTime(transactionDto.getUploadDateTime());
            transactionEntity.setBlockEntity(blockEntity);
            blockEntity.getTransactions().add(transactionEntity);
        });
        blockRepository.save(blockEntity);
    }

    //todo
    public boolean isThisBlockInSuccessfulBlocks(BlockDto thisBlock) {
        return SuccessfulMinedByOthersBlocks.getSuccessfulBlocks().stream().anyMatch(eachSuccessBlock -> {
            LinkedList<TransactionDto>
                    successTransactions = eachSuccessBlock.getTransactions();
            LinkedList<TransactionDto> toCheckTransactions = thisBlock.getTransactions();
            return true;
        });
    }

}