package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.BlockchainApi;
import io.swagger.model.Body;
import io.swagger.model.Body2;
import io.swagger.model.InlineResponse2001;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
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

    public ResponseEntity<InlineResponse2001> receiveMinedBlockInfoPost(Body2 body) {
        return null;
    }

    @Override
    public ResponseEntity<Object> receiveNonceErrorPost() {
        return null;
    }

    @Override
    public ResponseEntity<Object> stopMining(Body body) {
        return null;
    }
}