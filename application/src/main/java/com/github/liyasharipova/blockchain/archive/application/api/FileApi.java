package com.github.liyasharipova.blockchain.archive.application.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.InlineResponse2001;
import io.swagger.model.InlineResponse2002;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

public interface FileApi {

    /**
     * Получение файла по его id
     *
     * @param fileId
     * @return
     */
    @RequestMapping(value = "/files/{file-id}",
                    produces = {"application/octet-stream"},
                    method = RequestMethod.GET)
    ResponseEntity<Resource> getFile(
            @ApiParam(value = "", required = true) @PathVariable("file-id") Long fileId) throws IOException;

    /**
     * Отправка файла для загрузки
     *
     * @param file
     * @param redirectAttributes
     * @return Получение статуса загрузки
     */
    @RequestMapping(value = "/upload-file",
                    consumes = {"multipart/form-data"},
                    method = RequestMethod.POST)
    String uploadFile(
            @ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException;
}
