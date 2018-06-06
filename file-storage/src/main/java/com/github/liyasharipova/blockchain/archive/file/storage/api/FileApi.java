package com.github.liyasharipova.blockchain.archive.file.storage.api;

import com.github.liyasharipova.blockchain.archive.file.storage.dto.FileDto;
import com.github.liyasharipova.blockchain.archive.file.storage.dto.FileRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body1;
import io.swagger.model.InlineResponse200;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileApi {

    /**
     * Выгрузить все файлы
     */
    @RequestMapping(value = "/files",
                    produces = {"application/json"},
                    method = RequestMethod.GET)
    List<FileDto> getAllFiles();

    /**
     * Выгрузить файл по его id
     */
    @RequestMapping(value = "/files/{files-id}",
                    produces = {"application/json"},
                    consumes = {"application/x-www-form-urlencoded"},
                    method = RequestMethod.GET)
    ResponseEntity<Resource> getFileById(@PathVariable("file-id") Long fileId) throws IOException;

    /**
     * Загрузить файл
     *
     * @return file_id
     */
    @RequestMapping(value = "/upload-file",
                    produces = {"application/json"},
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    Long uploadFile(@ApiParam(value = "") @Valid @RequestBody FileRequest fileRequest);
}
