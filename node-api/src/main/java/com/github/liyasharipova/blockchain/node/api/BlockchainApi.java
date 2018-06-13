package com.github.liyasharipova.blockchain.node.api;

import com.github.liyasharipova.blockchain.node.api.dto.request.MiningInfoRequest;
import com.github.liyasharipova.blockchain.node.api.dto.request.NonceCheckRequest;
import com.github.liyasharipova.blockchain.node.api.dto.response.NonceCheckResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

public interface BlockchainApi {

    /**
     * Получение результата майнинга от ноды, которая первая смогла вычислить хеш заданной сложности
     *
     * @return Результат проверки полученного nonce путем вычисления хеша блока с помощью этого nonce
     */
    @RequestMapping(value = "/receive-mined-block-info",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    Boolean receiveMinedBlockInfoPost(@Valid @RequestBody NonceCheckRequest nonceCheckRequest);

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
    ResponseEntity<Object> stopMining(@Valid @RequestBody MiningInfoRequest miningInfoRequest);
}
