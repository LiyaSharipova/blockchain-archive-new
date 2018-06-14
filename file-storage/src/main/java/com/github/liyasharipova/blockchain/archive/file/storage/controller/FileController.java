package com.github.liyasharipova.blockchain.archive.file.storage.controller;

import com.github.liyasharipova.blockchain.archive.file.storage.service.FileService;
import com.github.liyasharipova.blockchain.filestorage.api.FileApi;
import com.github.liyasharipova.blockchain.filestorage.api.dto.FileDto;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

/**
 * Контроллер для файлов
 */
@Controller
public class FileController implements FileApi {

    @Autowired
    private FileService fileService;

    @Override
    public @ResponseBody
    List<FileDto> getAllFiles() {
        return fileService.getAllFiles();
    }

    @Override
    @RequestMapping(value = "/files/{file-id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getFileById(@NotBlank @PathVariable("file-id") Long fileId)
            throws IOException {
        AbstractMap.SimpleEntry<String, ByteArrayResource> nameAndFile = fileService.getFileById(fileId);
        // Формируем HTTP-response application service-у
        return ResponseEntity.ok()
                             .contentLength(nameAndFile.getValue().contentLength())
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .header("Content-Disposition", "attachment; filename=\"" + nameAndFile.getKey() + "\"")
                             .body(nameAndFile.getValue());

    }

    @Override
    @PostMapping(value = "/upload-file", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> uploadFile(@RequestBody FileDto fileDto) {
        return ResponseEntity.ok(fileService.saveFile(fileDto));
    }

}