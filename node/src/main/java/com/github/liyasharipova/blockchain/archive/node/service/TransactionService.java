package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.BlocksQueue;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Сервис для работы с транзакцией
 */
@Service
@Transactional
@Slf4j
public class TransactionService {

    /** Максимальное количество транзакций в блокчейне */
    @Value("${block.maximum.transactions}")
    private static int MAXIMUM_TRANSACTIONS_PER_BLOCK;

    /** Максимальное время паузы между добавлением транзакций в блок */
    @Value("${maximum.timeout.of.last.transaction.sec}")
    private static int MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION_SEC;

    private BlockDto currentBlock = new BlockDto();

    public Long processTransactionForBlockchain(TransactionDto transaction) {
        List<TransactionDto> currentTransactions = currentBlock.getTransactions();
        int currentTransactionsSize = currentTransactions.size();
        long currentTime = new Date().getTime();
        long lastTransactionTime = currentTransactions.get(currentTransactionsSize - 1).getUploadDateTime();
        long tenMinutesInSec = TimeUnit.SECONDS.toMillis(MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION_SEC);

        // Если текущий блок для добавления заполнен,
        // то добавляем его в очередь для добавления в блокчейн
        if (currentTransactionsSize == MAXIMUM_TRANSACTIONS_PER_BLOCK
                || (currentTime - lastTransactionTime) > tenMinutesInSec) {
            BlocksQueue.getBlocksQueue().add(currentBlock);
            currentBlock = new BlockDto();
        }

        currentBlock.addTransaction(transaction);
        BlocksQueue.getBlocksQueue().add(currentBlock);

        return transaction.getId();
    }
}