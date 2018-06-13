package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.application.api.BlockchainApi;
import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.application.api.dto.request.BlockchainRequest;
import com.github.liyasharipova.blockchain.archive.application.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 */
@RestController
public class BlockchainController implements BlockchainApi {

    @Autowired
    FileStorageService fileStorageService;

    @Override
    public ResponseEntity<Object> selfCheckResultPost(@Valid BlockchainRequest blockchainRequest) {
        return null;
    }

    @Override
    public void receiveMiningResult(@Valid BlockRequest blockRequest) {
        fileStorageService.sendBlock(blockRequest);
    }
}