package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.service.BlockService;
import com.github.liyasharipova.blockchain.node.api.dto.response.BlockDto;
import com.github.liyasharipova.blockchain.node.api.dto.response.SelfCheckResultDto;
import com.github.liyasharipova.blockchain.archive.node.service.NodeService;
import com.github.liyasharipova.blockchain.node.api.NodeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NodeController implements NodeApi {

    @Autowired
    NodeService nodeService;
    @Autowired
    BlockService blockService;

    @Override
    public ResponseEntity<Object> selfCheckBlockNumberPost(Long blockNumber, String fileHash) {
        SelfCheckResultDto checkResultDto = nodeService.selfCheck(blockNumber);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(checkResultDto);
    }

    @Override
    public ResponseEntity<Object> selfCheckGet() {
        SelfCheckResultDto checkResultDto = nodeService.selfCheck();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(checkResultDto);
    }

    @Override
    public ResponseEntity<List<BlockDto>> copyBlocks() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(blockService.getAllBlockDtos());
    }
}