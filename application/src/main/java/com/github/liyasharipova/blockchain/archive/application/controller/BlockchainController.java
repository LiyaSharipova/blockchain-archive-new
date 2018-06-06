package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.archive.application.api.BlockchainApi;
import com.github.liyasharipova.blockchain.archive.application.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.archive.application.dto.request.BlockchainRequest;
import io.swagger.model.Body1;
import io.swagger.model.Body2;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

/**
 *
 */
public class BlockchainController implements BlockchainApi {

    @Override
    public ResponseEntity<Object> selfCheckResultPost(@Valid BlockchainRequest blockchainRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Object> receiveMiningResult(@Valid BlockRequest blockRequest) {
        return null;
    }
}