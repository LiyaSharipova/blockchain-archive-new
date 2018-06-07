package com.github.liyasharipova.blockchain.archive.file.storage.controller;

import com.github.liyasharipova.blockchain.archive.file.storage.api.BlockApi;
import com.github.liyasharipova.blockchain.archive.file.storage.dto.BlockRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 *
 */
@Controller
public class BlockController implements BlockApi{

    @Override
    public ResponseEntity<Object> setBlocksPost(BlockRequest blockRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Object> receiveBlockNumberPost() {
        return null;
    }
}