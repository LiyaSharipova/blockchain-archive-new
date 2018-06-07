package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.BlockchainApi;
import com.github.liyasharipova.blockchain.archive.node.dto.request.MiningInfoRequest;
import com.github.liyasharipova.blockchain.archive.node.dto.request.NonceCheckRequest;
import com.github.liyasharipova.blockchain.archive.node.dto.response.NonceCheckResponse;
import com.github.liyasharipova.blockchain.archive.node.service.MiningResultCheckerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 *
 */
@Controller
@Slf4j
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