package com.github.liyasharipova.blockchain.archive.application.api;

import com.github.liyasharipova.blockchain.archive.application.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.archive.application.dto.request.BlockchainRequest;
import io.swagger.annotations.ApiParam;
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
            @ApiParam(value = "") @Valid @RequestBody BlockchainRequest blockchainRequest);

    /**
     * Получение номера блока и списков идентификаторов файлов в нем. Для передачи этой информации File Storage.
     *
     * @param blockRequest
     * @return
     */
    @RequestMapping(value = "/receive-mining-result",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<Object> receiveMiningResult(@ApiParam(value = "") @Valid @RequestBody BlockRequest blockRequest);
}
