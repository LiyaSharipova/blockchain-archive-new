package com.github.liyasharipova.blockchain.archive.application.controller;

import com.github.liyasharipova.blockchain.archive.application.exception.StorageFileNotFoundException;
import com.github.liyasharipova.blockchain.archive.application.service.DataService;
//import com.github.liyasharipova.blockchain.archive.hashing.blockchaindata.BlockchainDataService;
//import com.github.liyasharipova.blockchain.archive.hashing.transaction.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;

@Controller
public class FileUploadController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

//    private final BlockchainDataService blockchainDataService;

//    private final TransactionService transactionService;

    private DataService dataService;

//    @Autowired
//    public FileUploadController(BlockchainDataService blockchainDataService,
//                                TransactionService transactionService,
//                                DataService dataService) {
//        this.blockchainDataService = blockchainDataService;
//        this.transactionService = transactionService;
//        this.dataService = dataService;
//    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", dataService.loadAllFiles());

        return "uploadPage";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {

//        Resource file = transactionService.loadAsResource(filename);
//        return ResponseEntity.ok()
//                             .contentLength(file.contentLength())
//                             .contentType(MediaType.parseMediaType("application/octet-stream"))
//                             .body(file);
        return  null;
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   @RequestHeader("User-Agent") String userId) {

//        try {
//            long currentTime = new Date().getTime();
//            blockchainDataService.placeToBlockchain(file.getBytes(), file.getOriginalFilename(), userId, currentTime);
//        } catch (IOException e) {
//            LOGGER.warn("File uploading unsuccessful", e.getCause());
//        }
//        LOGGER.info("File {} saved in blockchain", file.getOriginalFilename());
//
//        redirectAttributes.addAttribute("message",
//                                        "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
