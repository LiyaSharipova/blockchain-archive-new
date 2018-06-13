package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.BlocksQueue;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Сервис для работы с транзакцией
 */
@Service
@Transactional
@Slf4j
public class TransactionService {

    /**
     * Максимальное количество транзакций в блокчейне
     */
    @Value("${block.maximum.transactions}")
    //todo убрать захардкоженные значения, но спринг не подставляет @Value
    private static int MAXIMUM_TRANSACTIONS_PER_BLOCK = 2;

    /**
     * Максимальное время паузы между добавлением транзакций в блок
     */
    @Value("${maximum.timeout.of.last.transaction.sec}")
    //todo убрать захардкоженные значения, но спринг не подставляет @Value
    private static int MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION_SEC = 600;

    /**
     * Храним текущий блок для заполнения транзакциями
     */
    private BlockDto currentBlock = new BlockDto();

    /**
     * Храним время последней загрузки транзакции.
     */
    private long lastUploadTime = -1L;

    /**
     * Положить транзакцию в блок, а если блок заполнен,
     * то поместить его в очередь {@link BlocksQueue} на майнинг
     */
    public void processTransactionForBlockchain(TransactionDto transaction) {
        // Список транзакций в текущем блок
        LinkedList<TransactionDto> currentTransactions = currentBlock.getTransactions();
        // Максимум таймаута в мсек
        long maxTimeoutMillisec = TimeUnit.SECONDS.toMillis(MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION_SEC);
        // Текущее время в мсек
        long currentTime = new Date().getTime();
        // Если текущий блок для добавления заполнен,
        // то добавляем его в очередь для добавления в блокчейн.
        // Или неьы в первый раз добавляем транзакцию,
        // которая произошла больше положенного таймаута после последней
        currentBlock.addTransaction(transaction);
        if (currentTransactions.size() == MAXIMUM_TRANSACTIONS_PER_BLOCK
                || (lastUploadTime != -1L && (currentTime - lastUploadTime) > maxTimeoutMillisec)) {
            BlocksQueue.getBlocksQueue().add(currentBlock);
            currentBlock = new BlockDto();
        }

    }
}