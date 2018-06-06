package com.github.liyasharipova.blockchain.archive.file.storage.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface BlockApi {

    @ApiOperation(value = "Проставление номера блока", nickname = "setBlocksPost",
                  notes = "Проставление номера блока в указанных файлах, чтобы знать об их местонахождении в сети блокчейн",
                  response = Object.class, tags = {"Block",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Object.class)})
    @RequestMapping(value = "/set-blocks",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<Object> setBlocksPost(@ApiParam(value = "") @Valid @RequestBody Body body);

    @ApiOperation(value = "Получить номер блока ", nickname = "receiveBlockNumberPost", notes = "",
                  response = Object.class, tags = {"Block",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Object.class)})
    @RequestMapping(value = "/receive-block-number",
                    method = RequestMethod.POST)
    ResponseEntity<Object> receiveBlockNumberPost();
}
