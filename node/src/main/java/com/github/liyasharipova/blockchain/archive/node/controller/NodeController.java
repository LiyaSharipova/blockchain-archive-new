package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.NodeApi;
import com.github.liyasharipova.blockchain.archive.node.service.BlockchainService;
import io.swagger.model.Body1;
import io.swagger.model.InlineResponse2002;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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