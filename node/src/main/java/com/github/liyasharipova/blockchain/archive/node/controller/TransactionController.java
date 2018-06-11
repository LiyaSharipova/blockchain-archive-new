package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.node.api.TransactionApi;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import com.github.liyasharipova.blockchain.archive.node.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 */
@Controller
public class TransactionController implements TransactionApi {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(
            TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public Long receiveHash(TransactionDto transaction) {
        return transactionService.processTransactionForBlockchain(transaction);
    }
}