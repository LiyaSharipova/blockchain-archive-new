package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.application.api.BlockchainApi;
import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.application.api.dto.request.BlockchainRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 *
 */
@Controller
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