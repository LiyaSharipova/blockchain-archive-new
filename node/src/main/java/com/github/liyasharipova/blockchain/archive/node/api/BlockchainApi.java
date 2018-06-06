package com.github.liyasharipova.blockchain.archive.node.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body;
import io.swagger.model.Body2;
import io.swagger.model.InlineResponse2001;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

public interface BlockchainApi {

    @ApiOperation(value = "Копирование блоков", nickname = "copyBlocks",
                  notes = "Запрос на копирование корректные блоков в случае ошибки во время майнинга",
                  response = Object.class, responseContainer = "List", tags = {"Block",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Object.class, responseContainer = "List")})
    @RequestMapping(value = "/copy-blocks",
                    method = RequestMethod.GET)
    ResponseEntity<List<Object>> copyBlocks();

    @ApiOperation(value = "Получение результата майнинга ", nickname = "receiveMinedBlockInfoPost",
                  notes = "Получение результата майнинга от ноды, которая первая смогла вычислить хеш заданной сложности",
                  response = InlineResponse2001.class, tags = {"Block",})
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                         message = "Результат проверки полученного nonce путем вычисления хеша блока с помощью этого nonce",
                         response = InlineResponse2001.class)})
    @RequestMapping(value = "/receive-mined-block-info",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<InlineResponse2001> receiveMinedBlockInfoPost(@ApiParam(value = "") @Valid @RequestBody Body2 body);

    @ApiOperation(value = "Получение ошибки проверки nonce", nickname = "receiveNonceErrorPost",
                  notes = "Получение ошибки проверки полученного nonce от ноды, которая первая смогла вычислить хеш заданной сложности, для дальнейшего запуска self-check. Метод будет альтернативой синхронного ответа вместо отправки response c /receive-mined-block-info",
                  response = Object.class, tags = {"Block",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Object.class)})
    @RequestMapping(value = "/receive-nonce-error",
                    method = RequestMethod.POST)
    ResponseEntity<Object> receiveNonceErrorPost();

    @ApiOperation(value = "Остановка майнинга", nickname = "stopMining", notes = "Завершение майнинга на ноде",
                  response = Object.class, tags = {"Block",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = Object.class)})
    @RequestMapping(value = "/stop-mining",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<Object> stopMining(@ApiParam(value = "") @Valid @RequestBody Body body);
}
