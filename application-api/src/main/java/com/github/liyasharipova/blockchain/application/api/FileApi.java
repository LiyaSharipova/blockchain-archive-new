package com.github.liyasharipova.blockchain.application.api;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
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
             @PathVariable("file-id") Long fileId) throws IOException;

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
             @Valid @RequestPart("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException;
}
