package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.service.TransactionService;
import com.github.liyasharipova.blockchain.node.api.TransactionApi;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

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
    public ResponseEntity<Long> receiveHash(@Valid @RequestBody TransactionDto transaction) {
        Long transactionId = transactionService.processTransactionForBlockchain(transaction);
        return ResponseEntity.ok(transactionId);
    }
}