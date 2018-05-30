package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.archive.application.exception.StorageFileNotFoundException;
import com.github.liyasharipova.blockchain.archive.application.service.FileStorageService;
import com.github.liyasharipova.blockchain.archive.application.service.HashingService;
import com.github.liyasharipova.blockchain.archive.application.service.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@Slf4j
public class FileController {

    private FileStorageService fileStorageService;

    private NodeService nodeService;

    private HashingService hashingService;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    public FileController(
            FileStorageService fileStorageService,
            NodeService nodeService,
            HashingService hashingService) {
        this.fileStorageService = fileStorageService;
        this.nodeService = nodeService;
        this.hashingService = hashingService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", fileStorageService.getAllFiles());

        return "uploadPage";
    }

    @GetMapping("/files/{file-hash:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileHash) throws IOException {

        Resource file = fileStorageService.getFile(fileHash);

        return ResponseEntity.ok()
                             .contentLength(file.contentLength())
                             .contentType(MediaType.parseMediaType("application/octet-stream"))
                             .body(file);
    }

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {

        try {
            String hash = hashingService.hash(file.getBytes());

            //Отправить параллельно файл в хранилище данных
            executorService.submit(() -> fileStorageService.uploadFile(file, hash));

            //Отправить параллельно его хэш всем нодам
            executorService.submit(() -> nodeService.sendHash(hash));

        } catch (IOException e) {
            log.warn(e.getMessage(), e.getCause());
            throw e;
        }

        redirectAttributes.addAttribute("message",
                                        "You successfully uploaded " + file.getOriginalFilename());

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
