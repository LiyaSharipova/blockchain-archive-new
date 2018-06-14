package com.github.liyasharipova.blockchain.archive.file.storage.controller;

import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.archive.file.storage.service.FileService;
import com.github.liyasharipova.blockchain.filestorage.api.BlockApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 *
 */
@RestController
public class BlockController implements BlockApi {

    @Autowired
    FileService fileService;

    @Override
    public void setBlocksPost(@Valid @RequestBody BlockRequest blockRequest) {
        fileService.setBlocks(blockRequest);
    }

    @Override
    public ResponseEntity<UUID> receiveBlockNumberPost() {
        return null;
    }
}