package com.github.liyasharipova.blockchain.archive.application.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body1;
import io.swagger.model.Body2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface BlockchainApi {

    @ApiOperation(value = "Принятие результата проверки", nickname = "selfCheckResultPost",
                  notes = "Принятие результата проверки цепочки блоков с указанием номера ноды для контроля корректного состояния всех нод",
                  response = Object.class, tags = {"Blockchain",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Object.class)})
    @RequestMapping(value = "/self-check-result",
                    method = RequestMethod.POST)
    ResponseEntity<Object> selfCheckResultPost(@ApiParam(value = "") @Valid @RequestBody Body1 body);

    @ApiOperation(value = "Получение результатов майнинга", nickname = "receiveMiningResult",
                  notes = "Получение номера блока и списков идентификаторов файлов в нем. Для передачи этой информации File Storage.",
                  response = Object.class, tags = {"Block",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Object.class)})
    @RequestMapping(value = "/receive-mining-result",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<Object> receiveMiningResult(@ApiParam(value = "") @Valid @RequestBody Body2 body);
}
