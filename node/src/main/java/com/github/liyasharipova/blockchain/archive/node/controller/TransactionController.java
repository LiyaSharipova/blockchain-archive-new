package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.TransactionApi;
import com.github.liyasharipova.blockchain.archive.node.service.TransactionService;
import io.swagger.model.Hash;
import io.swagger.model.InlineResponse200;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * Контроллер, в
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
    public ResponseEntity<InlineResponse200> receiveHash(Hash hash) {

        return ResponseEntity.ok(transactionService.processHashForBlockchain(hash));
    }
}