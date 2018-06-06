package com.github.liyasharipova.blockchain.archive.application.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.InlineResponse2001;
import io.swagger.model.InlineResponse2002;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

public interface FileApi {

    @ApiOperation(value = "Получение файла", nickname = "getFile", notes = "Получение файла по его id",
                  response = InlineResponse2002.class, tags = {"File",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = InlineResponse2002.class)})
    @RequestMapping(value = "/files/{file-id}",
                    produces = {"application/octet-stream"},
                    method = RequestMethod.GET)
    ResponseEntity<InlineResponse2002> getFile(
            @ApiParam(value = "", required = true) @PathVariable("file-id") Long fileId);

    @ApiOperation(value = "Загрузка файла", nickname = "uploadFile", notes = "Отправка файла для загрузки",
                  response = InlineResponse2001.class, tags = {"File",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Получение статуса загрузки", response = InlineResponse2001.class)})
    @RequestMapping(value = "/upload-file",
                    consumes = {"multipart/form-data"},
                    method = RequestMethod.POST)
    ResponseEntity<InlineResponse2001> uploadFile(
            @ApiParam(value = "") @RequestHeader(value = "User-Agent", required = false) String userAgent,
            @ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file);
}
