package com.github.liyasharipova.blockchain.archive.node.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body1;
import io.swagger.model.InlineResponse2002;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface NodeApi {
    @ApiOperation(value = "Проверка до заданного блока", nickname = "selfCheckBlockNumberPost", notes = "Проверка валидноси цепочки блоков до заданного блока и наличия хеша в указанном блоке", response = Object.class, tags={ "Blockchain", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Object.class) })
    @RequestMapping(value = "/self-check/{block-number}",
                    consumes = { "application/json" },
                    method = RequestMethod.POST)
    ResponseEntity<Object> selfCheckBlockNumberPost(@ApiParam(value = "Номер блока, до которого будет проверка", required=true) @PathVariable("block-number") Long blockNumber, @ApiParam(value = ""  )  @Valid @RequestBody Body1 body);


    @ApiOperation(value = "Проверка валидности цепи", nickname = "selfCheckGet", notes = "Проверка валидности всей цепочки блоков", response = InlineResponse2002.class, tags={ "Blockchain", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешная проверка цепи", response = InlineResponse2002.class),
            @ApiResponse(code = 500, message = "При проверке цепи выявлено несоответствие") })
    @RequestMapping(value = "/self-check",
                    method = RequestMethod.GET)
    ResponseEntity<InlineResponse2002> selfCheckGet();
}
