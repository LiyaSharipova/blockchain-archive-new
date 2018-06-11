package com.github.liyasharipova.blockchain.node.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface NodeApi {

    /**
     * Проверка валидности цепочки блоков до заданного блока и наличия хеша в указанном блоке
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
