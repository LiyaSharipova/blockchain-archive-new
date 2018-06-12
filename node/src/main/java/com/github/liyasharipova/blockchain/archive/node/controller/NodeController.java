package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.dto.SelfCheckResultDto;
import com.github.liyasharipova.blockchain.archive.node.service.NodeService;
import com.github.liyasharipova.blockchain.node.api.NodeApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NodeController implements NodeApi {

    @Autowired
    NodeService nodeService;

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
    public ResponseEntity<List<Object>> copyBlocks() {
        return null;
    }
}