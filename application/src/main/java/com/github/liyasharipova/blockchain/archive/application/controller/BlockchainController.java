package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.archive.application.api.BlockchainApi;
import io.swagger.model.Body1;
import io.swagger.model.Body2;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

/**
 *
 */
public class BlockchainController implements BlockchainApi {

    @Override
    public ResponseEntity<Object> selfCheckResultPost(@Valid Body1 body) {
        return null;
    }

    @Override
    public ResponseEntity<Object> receiveMiningResult(@Valid Body2 body) {
        return null;
    }
}