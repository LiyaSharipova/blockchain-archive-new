package com.github.liyasharipova.blockchain.archive.file.storage.controller;

import com.github.liyasharipova.blockchain.archive.file.storage.api.FileApi;
import com.github.liyasharipova.blockchain.archive.file.storage.dto.FileDto;
import com.github.liyasharipova.blockchain.archive.file.storage.dto.FileRequest;
import com.github.liyasharipova.blockchain.archive.file.storage.service.FileService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

/**
 * Контроллер для файлов
 */
@Controller
@RequestMapping(value = "/files")
public class FileController implements FileApi {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public @ResponseBody
    List<FileDto> getAllFiles() {
        return fileService.getAllFiles();
    }

    @Override
    @RequestMapping(value = "/files/{file-hash}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getFileById(@NotBlank @PathParam("file-id") Long id)
            throws IOException {
        Resource file = fileService.getFileById(id);
        // Формируем HTTP-response application service-у
        return ResponseEntity.ok()
                             .contentLength(file.contentLength())
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .body(file);

    }

    @Override
    @PostMapping(value = "/upload-file", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long uploadFile(FileRequest fileRequest) {
        fileService.saveFile(fileRequest.getFile(), fileRequest.getHash());
        return null;
    }

}