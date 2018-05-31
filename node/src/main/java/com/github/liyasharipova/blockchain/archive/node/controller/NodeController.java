package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.service.BlockchainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class NodeController {

    private BlockchainService transactionService;

    @Autowired
    public NodeController(
            BlockchainService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/receive-hash", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity receiveHash(@RequestParam("hash") String hash) {
        transactionService.putHashIntoTransaction(hash);
        return ResponseEntity.ok().build();
    }

}