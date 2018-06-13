package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.service.MiningResultCheckerService;
import com.github.liyasharipova.blockchain.archive.node.service.StopMiningService;
import com.github.liyasharipova.blockchain.node.api.BlockchainApi;
import com.github.liyasharipova.blockchain.node.api.dto.request.MiningInfoRequest;
import com.github.liyasharipova.blockchain.node.api.dto.request.NonceCheckRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 */
@RestController
public class BlockchainController implements BlockchainApi {

    private final StopMiningService stopMiningService = new StopMiningService();

    private MiningResultCheckerService miningResultCheckerService;

    @Autowired
    public BlockchainController(
            MiningResultCheckerService miningResultCheckerService) {
        this.miningResultCheckerService = miningResultCheckerService;
    }

    @Override
    public Boolean receiveMinedBlockInfoPost(@Valid @RequestBody NonceCheckRequest nonceCheckRequest) {
        Boolean response = miningResultCheckerService.checkMinedBlockInfo(nonceCheckRequest);
        return response;
    }

    @Override
    public ResponseEntity<Object> receiveNonceErrorPost() {
        return null;
    }

    @Override
    @Deprecated
    public ResponseEntity<Object> stopMining(MiningInfoRequest miningInfoRequest) {
        return null;
    }
}