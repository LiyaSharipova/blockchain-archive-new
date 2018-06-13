package com.github.liyasharipova.blockchain.application.api;

import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.application.api.dto.request.BlockchainRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface BlockchainApi {

    /**
     * Принятие результата проверки цепочки блоков с указанием номера ноды для контроля корректного состояния всех нод
     *
     * @param blockchainRequest
     * @return
     */

    @RequestMapping(value = "/self-check-result",
                    method = RequestMethod.POST)
    ResponseEntity<Object> selfCheckResultPost(
            @Valid @RequestBody BlockchainRequest blockchainRequest);

    /**
     * Получение номера блока и списков идентификаторов файлов в нем. Для передачи этой информации File Storage.
     *
     * @param blockRequest
     * @return
     */
    @RequestMapping(value = "/receive-mining-result",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    void receiveMiningResult(@Valid @RequestBody BlockRequest blockRequest);
}
