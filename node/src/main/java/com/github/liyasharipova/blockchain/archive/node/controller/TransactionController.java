package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.service.TransactionService;
import com.github.liyasharipova.blockchain.node.api.TransactionApi;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 */
@RestController
public class TransactionController implements TransactionApi {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(
            TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void receiveHash(@Valid @RequestBody TransactionDto transaction) {
        transactionService.processTransactionForBlockchain(transaction);
    }
}