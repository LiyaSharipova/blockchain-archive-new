package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.NodeApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class NodeController implements NodeApi {

    @Override
    public ResponseEntity<Object> selfCheckBlockNumberPost(Long blockNumber, String fileHash) {
        return null;
    }

    @Override
    public Long selfCheckGet() {
        return null;
    }
}