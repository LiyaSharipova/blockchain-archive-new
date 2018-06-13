package com.github.liyasharipova.blockchain.archive.file.storage.controller;

import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.archive.file.storage.service.FileService;
import com.github.liyasharipova.blockchain.filestorage.api.BlockApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 *
 */
@Controller
public class BlockController implements BlockApi{

    @Autowired
    FileService fileService;

    @Override
    public void setBlocksPost(BlockRequest blockRequest) {
        fileService.setBlocks(blockRequest);
    }

    @Override
    public ResponseEntity<UUID> receiveBlockNumberPost() {
        return null;
    }
}