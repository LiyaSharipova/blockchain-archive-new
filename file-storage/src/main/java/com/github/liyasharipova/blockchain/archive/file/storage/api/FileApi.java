package com.github.liyasharipova.blockchain.archive.file.storage.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body1;
import io.swagger.model.InlineResponse200;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

public interface FileApi {

    @ApiOperation(value = "Выгрузить все файлы", nickname = "getAllFiles", notes = "", response = java.io.File.class,
                  responseContainer = "List", tags = {"File",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = java.io.File.class, responseContainer = "List")})
    @RequestMapping(value = "/files",
                    produces = {"application/json"},
                    method = RequestMethod.GET)
    ResponseEntity<List<File>> getAllFiles();

    @ApiOperation(value = "Выгрузить файл по его id", nickname = "getFileByHash", notes = "",
                  response = java.io.File.class, tags = {"File",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = java.io.File.class)})
    @RequestMapping(value = "/files/{files-id}",
                    produces = {"application/json"},
                    consumes = {"application/x-www-form-urlencoded"},
                    method = RequestMethod.GET)
    ResponseEntity<java.io.File> getFileByHash(
            @ApiParam(value = "Хэш файла", required = true) @PathVariable("file-id") Long fileId);

    @ApiOperation(value = "Загрузить файл", nickname = "uploadFile", notes = "", response = InlineResponse200.class,
                  tags = {"File",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "****", response = InlineResponse200.class)})
    @RequestMapping(value = "/upload-file",
                    produces = {"application/json"},
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    ResponseEntity<InlineResponse200> uploadFile(@ApiParam(value = "") @Valid @RequestBody Body1 body);
}
