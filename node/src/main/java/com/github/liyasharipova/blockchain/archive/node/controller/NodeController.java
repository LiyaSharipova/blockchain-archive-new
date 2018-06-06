package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.NodeApi;
import com.github.liyasharipova.blockchain.archive.node.service.BlockchainService;
import io.swagger.model.Body1;
import io.swagger.model.InlineResponse2002;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class NodeController implements NodeApi {

    private BlockchainService transactionService;

    @Autowired
    public NodeController(
            BlockchainService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/receive-hash", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity receiveHash(@RequestParam("hash") String hash) {
//        transactionService.mineBlockAndPlaceToBlockchain(hash);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> selfCheckBlockNumberPost(Long blockNumber, Body1 body) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse2002> selfCheckGet() {
        return null;
    }
}