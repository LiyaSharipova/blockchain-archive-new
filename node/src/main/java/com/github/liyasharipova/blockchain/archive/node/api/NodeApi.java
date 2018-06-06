package com.github.liyasharipova.blockchain.archive.node.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body1;
import io.swagger.model.InlineResponse2002;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface NodeApi {

    /**
     * Проверка валидноси цепочки блоков до заданного блока и наличия хеша в указанном блоке
     *
     * @param blockNumber Номер блока, до которого будет проверка
     */

    @RequestMapping(value = "/self-check/{block-number}",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<Object> selfCheckBlockNumberPost(@PathVariable("block-number") Long blockNumber,
                                                    @Valid @RequestParam("file-hash") String fileHash);

    /**
     * Проверка валидности всей цепочки блоков
     *
     * @return
     * code = 200, message = "Успешная проверка цепи",
     * code = 500, message = "При проверке цепи выявлено несоответствие"
     */
    @RequestMapping(value = "/self-check",
                    method = RequestMethod.GET)
    Long selfCheckGet();
}
