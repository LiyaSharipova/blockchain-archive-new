package com.github.liyasharipova.blockchain.archive.application.api;

import com.github.liyasharipova.blockchain.archive.application.dto.NonceRangeDto;
import com.github.liyasharipova.blockchain.archive.application.dto.request.NonceRequest;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface NonceApi {

    /**
     * Получение каждой нодой диапазона nonce для майнинга
     *
     * @param nonceRequest
     * @return
     */
    @RequestMapping(value = "/nonces",
                    method = RequestMethod.GET)
    ResponseEntity<NonceRangeDto> getNonces(@ApiParam(value = "") @Valid @RequestBody NonceRequest nonceRequest);
}
