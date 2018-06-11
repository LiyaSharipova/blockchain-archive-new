package com.github.liyasharipova.blockchain.filestorage.api;

import com.github.liyasharipova.blockchain.filestorage.api.dto.FileDto;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @PostMapping(value = "/upload-file", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Long> uploadFile(FileDto file) throws IOException;
}
