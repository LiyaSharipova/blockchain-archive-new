package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlocksQueue;
import com.github.liyasharipova.blockchain.archive.node.dto.FutureBlock;
import io.swagger.model.Hash;
import io.swagger.model.InlineResponse200;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Сервис для работы с транзакцией
 */
@Service
@Transactional
@Slf4j
public class TransactionService {

    @Value("block.maximum.transactions")
    private String blockMaximumTransactions;

    private FutureBlock currentBlock = new FutureBlock();

    public InlineResponse200 processHashForBlockchain(Hash hash) {

        // Если текущий блок для добавления заполнен,
        // то добавляем его в очередь для добавления в блокчейн
        if (currentBlock.getHashes().size() == 4) {
            BlocksQueue.getBlocksQueue().add(currentBlock);
            currentBlock = new FutureBlock();

        }

        BlocksQueue.getBlocksQueue().add(currentBlock);

        return null;
    }
}