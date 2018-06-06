package com.github.liyasharipova.blockchain.archive.application.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body;
import io.swagger.model.InlineResponse200;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface NonceApi {

    @ApiOperation(value = "Получение nonce", nickname = "getNonces",
                  notes = "Получение каждой нодой диапазона nonce для майнинга", response = InlineResponse200.class,
                  tags = {"Nonce",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = InlineResponse200.class)})
    @RequestMapping(value = "/nonces",
                    method = RequestMethod.GET)
    ResponseEntity<InlineResponse200> getNonces(@ApiParam(value = "") @Valid @RequestBody Body body);
}
