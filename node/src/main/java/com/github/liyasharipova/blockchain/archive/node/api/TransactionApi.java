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

    /**
     * Получить хэш и id файла для добавления его в блок
     *
     * @param body
    //TODO  подумать зачем
     * @return id файла
     */
    @RequestMapping(value = "/receive-file-info",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    Long receiveHash(@Valid @RequestBody Hash body);
}
