package com.github.liyasharipova.blockchain.archive.file.storage.controller;

import com.github.liyasharipova.blockchain.archive.file.storage.dto.FileDto;
import com.github.liyasharipova.blockchain.archive.file.storage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody  List<FileDto> getAllFiles() {
        return fileService.getAllFiles();
    }

    @RequestMapping(value = "/{file-hash}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getFileByHash(@PathParam("file-hash") String fileHash) throws IOException {
        Resource file = fileService.getFileByHash(fileHash);
        return ResponseEntity.ok()
                             .contentLength(file.contentLength())
                             .contentType(MediaType.parseMediaType("application/octet-stream"))
                             .body(file);

    }
}