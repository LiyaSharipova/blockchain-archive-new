package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.BlockchainApi;
import com.github.liyasharipova.blockchain.archive.node.dto.request.MiningInfoRequest;
import com.github.liyasharipova.blockchain.archive.node.dto.request.NonceCheckRequest;
import com.github.liyasharipova.blockchain.archive.node.dto.response.NonceCheckResponse;
import io.swagger.model.InlineResponse2001;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *
 */
@Slf4j
public class BlockchainController implements BlockchainApi {

    @Override
    public ResponseEntity<List<Object>> copyBlocks() {
        return null;
    }

    @Override
    public ResponseEntity<NonceCheckResponse> receiveMinedBlockInfoPost(NonceCheckRequest nonceCheckRequest) {
        return null;
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