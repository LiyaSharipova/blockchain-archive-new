package com.github.liyasharipova.blockchain.archive.file.storage.controller;

import com.github.liyasharipova.blockchain.archive.file.storage.api.FileApi;
import com.github.liyasharipova.blockchain.archive.file.storage.dto.FileDto;
import com.github.liyasharipova.blockchain.archive.file.storage.service.FileService;
import io.swagger.model.Body1;
import io.swagger.model.InlineResponse200;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Контроллер для файлов
 */
@Controller
@RequestMapping(value = "/files")
public class FileController implements FileApi{

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public @ResponseBody
    List<FileDto> getAllFiles() {
        return fileService.getAllFiles();
    }

    @RequestMapping(value = "/files/{file-hash}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@NotBlank @PathParam("file-hash") String fileHash)
            throws IOException {
        Resource file = fileService.getFileByHash(fileHash);
        // Формируем HTTP-response application service-у
        return ResponseEntity.ok()
                             .contentLength(file.contentLength())
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .body(file);

    }

    @PostMapping(value = "/upload-file", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("hash") String hash) {
        fileService.saveFile(file, hash);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<File> getFileByHash(Long fileId) {
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse200> uploadFile(Body1 body) {
        return null;
    }
}