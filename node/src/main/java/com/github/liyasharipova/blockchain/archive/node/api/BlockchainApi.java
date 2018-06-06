package com.github.liyasharipova.blockchain.archive.node.api;

import com.github.liyasharipova.blockchain.archive.node.dto.request.MiningInfoRequest;
import com.github.liyasharipova.blockchain.archive.node.dto.request.NonceCheckRequest;
import com.github.liyasharipova.blockchain.archive.node.dto.response.NonceCheckResponse;
import io.swagger.annotations.ApiParam;
import io.swagger.model.InlineResponse2001;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

public interface BlockchainApi {

    /**
     * Запрос на копирование корректные блоков в случае ошибки во время майнинга
     */
    @RequestMapping(value = "/copy-blocks",
                    method = RequestMethod.GET)
    ResponseEntity<List<Object>> copyBlocks();

    /**
     * Получение результата майнинга от ноды, которая первая смогла вычислить хеш заданной сложности
     *
     * @return Результат проверки полученного nonce путем вычисления хеша блока с помощью этого nonce
     */
    @RequestMapping(value = "/receive-mined-block-info",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<NonceCheckResponse> receiveMinedBlockInfoPost(@ApiParam(value = "") @Valid @RequestBody NonceCheckRequest nonceCheckRequest);

    /**
     * Получение ошибки проверки полученного nonce от ноды, которая первая смогла вычислить хеш заданной сложности, для дальнейшего запуска self-check. Метод будет альтернативой синхронного ответа вместо отправки response c /receive-mined-block-info
     */
    @RequestMapping(value = "/receive-nonce-error",
                    method = RequestMethod.POST)
    ResponseEntity<Object> receiveNonceErrorPost();

    /**
     * Завершение майнинга на ноде
     */
    @RequestMapping(value = "/stop-mining",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<Object> stopMining(@ApiParam(value = "") @Valid @RequestBody MiningInfoRequest miningInfoRequest);
}
