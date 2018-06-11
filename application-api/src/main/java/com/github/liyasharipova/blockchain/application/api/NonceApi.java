package com.github.liyasharipova.blockchain.application.api;

import com.github.liyasharipova.blockchain.application.api.dto.NonceRangeDto;
import com.github.liyasharipova.blockchain.application.api.dto.request.NonceRequest;
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
    ResponseEntity<NonceRangeDto> getNonces(@Valid @RequestBody NonceRequest nonceRequest);
}
