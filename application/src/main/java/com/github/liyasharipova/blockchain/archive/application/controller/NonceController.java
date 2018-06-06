package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.archive.application.api.NonceApi;
import com.github.liyasharipova.blockchain.archive.application.dto.NonceRangeDto;
import com.github.liyasharipova.blockchain.archive.application.service.NonceService;
import io.swagger.model.Body;
import io.swagger.model.InlineResponse200;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

/**
 * Получение диапазона nonce
 */
@Controller
@Slf4j
public class NonceController implements NonceApi{

    private NonceService nonceService;

    @Autowired
    public NonceController(NonceService nonceService) {
        this.nonceService = nonceService;
    }

    @GetMapping("/nonces")
    @ResponseBody
    public ResponseEntity<NonceRangeDto> serveFile(@NonNull @PositiveOrZero @RequestParam Integer nodeId,
                                                   @NonNull @PositiveOrZero @RequestParam Long blockId) {

        NonceRangeDto nonceRangeDto = nonceService.calculateNonceRange(nodeId, blockId);

        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(nonceRangeDto);
    }

    @Override
    public ResponseEntity<InlineResponse200> getNonces(@Valid Body body) {
        return null;
    }
}