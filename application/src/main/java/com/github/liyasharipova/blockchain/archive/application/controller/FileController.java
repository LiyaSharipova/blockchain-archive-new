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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String listUploadedFiles(Model model) {
        model.addAttribute("files", fileStorageService.getAllFiles());
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
     * todo поместить сообщение о загрузке файла на страницу
     * todo выводить рядом с файлом статус загрузки в блокчейн - есть ли номер(хеш) блока
     * в файл сторидже
     * todo в зависимости от статуса убирать ссылку на скачивание, если файла еще нет в бч
     * todo проверить, что что-то отправили и вывести соответствующее сообщение
     */
    @PostMapping(value = "/",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileFromRoot(@RequestParam("file") MultipartFile file,
                                     RedirectAttributes redirectAttributes) throws IOException {

        uploadFileProcessing(file, redirectAttributes);

        return "redirect:/";
    }

    @PostMapping(value = "/upload-file",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) throws IOException {

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

        log.info("Файл и хэш были отправлены в хранилище и нодам: {}");

        redirectAttributes.addAttribute("message",
                                        "Вы успешно загрузили файл с именем " + file.getOriginalFilename());
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
