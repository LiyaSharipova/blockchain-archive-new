package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.node.api.BlockchainApi;
import com.github.liyasharipova.blockchain.node.api.dto.request.MiningInfoRequest;
import com.github.liyasharipova.blockchain.node.api.dto.request.NonceCheckRequest;
import com.github.liyasharipova.blockchain.node.api.dto.response.NonceCheckResponse;
import com.github.liyasharipova.blockchain.archive.node.service.MiningResultCheckerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class BlockchainController implements BlockchainApi {

    private MiningResultCheckerService miningResultCheckerService;

    @Autowired
    public BlockchainController(
            MiningResultCheckerService miningResultCheckerService) {
        this.miningResultCheckerService = miningResultCheckerService;
    }

    @Override
    public ResponseEntity<List<Object>> copyBlocks() {
        return null;
    }

    @Override
    public ResponseEntity<NonceCheckResponse> receiveMinedBlockInfoPost(NonceCheckRequest nonceCheckRequest) {
        NonceCheckResponse response = miningResultCheckerService.checkMinedBlockInfo(nonceCheckRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Object> receiveNonceErrorPost() {
        return null;
    }

    @Override
    public ResponseEntity<Object> stopMining(MiningInfoRequest miningInfoRequest) {
        return null;
    }
}