package com.github.liyasharipova.blockchain.archive.file.storage.controller;

import com.github.liyasharipova.blockchain.archive.file.storage.api.BlockApi;
import io.swagger.model.Body;
import org.springframework.http.ResponseEntity;

/**
 *
 */
public class BlockController implements BlockApi{

    @Override
    public ResponseEntity<Object> setBlocksPost(Body body) {
        return null;
    }

    @Override
    public ResponseEntity<Object> receiveBlockNumberPost() {
        return null;
    }
}