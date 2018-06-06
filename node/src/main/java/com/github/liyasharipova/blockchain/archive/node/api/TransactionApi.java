package com.github.liyasharipova.blockchain.archive.node.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Hash;
import io.swagger.model.InlineResponse200;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface TransactionApi {

    @ApiOperation(value = "Получение хэша и id", nickname = "receiveHash",
                  notes = "Получить хэш и id файла для добавления его в блок", response = InlineResponse200.class,
                  tags = {"Transaction",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = InlineResponse200.class)})
    @RequestMapping(value = "/receive-file-info",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<InlineResponse200> receiveHash(@ApiParam(value = "") @Valid @RequestBody Hash hash);
}
