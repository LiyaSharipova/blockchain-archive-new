package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.TransactionApi;
import com.github.liyasharipova.blockchain.archive.node.dto.request.Transaction;
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
    public Long receiveHash(Transaction transaction) {
        return transactionService.processTransactionForBlockchain(transaction);
    }
}