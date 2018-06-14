package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.application.api.FileApi;
import com.github.liyasharipova.blockchain.archive.application.exception.StorageFileNotFoundException;
import com.github.liyasharipova.blockchain.archive.application.service.FileStorageService;
import com.github.liyasharipova.blockchain.archive.application.service.HashingService;
import com.github.liyasharipova.blockchain.archive.application.service.NodeService;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@Slf4j
public class FileController implements FileApi {

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
    public String listUploadedFiles(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("files", fileStorageService.getAllFiles());
        model.addAttribute("message", message);
        return "uploadPage";
    }

    @Deprecated
    @GetMapping("/files")
    public String listUploadedFilesFromRoot() {
        return "redirect:/";
    }

    @GetMapping("/files/{file-id}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@NotBlank @PathVariable("file-id") Long fileId) throws IOException {
        Resource file = fileStorageService.getFile(fileId);
        return ResponseEntity.ok()
                .contentLength(file.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }

    /**
     * Заглушка, так как на uploadFile.jsp урл всегда /
     */
    @PostMapping(value = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileFromRoot(@RequestParam("file") MultipartFile file,
                                     RedirectAttributes redirectAttributes, Model model) throws IOException {

        uploadFileProcessing(file, redirectAttributes);

        return "redirect:/";
    }

    @PostMapping(value = "/upload-file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (file == null) {
            redirectAttributes.addFlashAttribute("message", "File is not uploaded");
            return "redirect:/";
        }
        uploadFileProcessing(file, redirectAttributes);

        return "redirect:/";
    }

    private void uploadFileProcessing(MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {
        try {

            String hash = hashingService.hash(file.getBytes());

            //Отправить параллельно файл в хранилище данных
            Long fileId = fileStorageService.uploadFile(file, hash);
            TransactionDto transaction = new TransactionDto(fileId, hash, new Date().getTime());
            //Отправить параллельно его хэш всем нодам
            nodeService.sendFileInfo(transaction);

        } catch (IOException e) {
            log.warn(e.getMessage(), e.getCause());
            throw e;
        }

        log.info("Файл и хэш были отправлены в хранилище и нодам");

        redirectAttributes.addFlashAttribute("message",
                "Вы успешно загрузили файл с именем " + file.getOriginalFilename());
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
