package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.application.api.NonceApi;
import com.github.liyasharipova.blockchain.application.api.dto.NonceRangeDto;
import com.github.liyasharipova.blockchain.application.api.dto.request.NonceRequest;
import com.github.liyasharipova.blockchain.archive.application.service.NonceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Получение диапазона nonce
 */
@Controller
@Slf4j
public class NonceController implements NonceApi {

    private NonceService nonceService;

    @Autowired
    public NonceController(NonceService nonceService) {
        this.nonceService = nonceService;
    }

    @PostMapping("/nonces")
    @ResponseBody
    public ResponseEntity<NonceRangeDto> getNonces(@RequestBody NonceRequest nonceRequest) {
        NonceRangeDto nonceRangeDto =
                nonceService.calculateNonceRange(nonceRequest.getNodeId(), nonceRequest.getBlockId());

        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(nonceRangeDto);
    }
}