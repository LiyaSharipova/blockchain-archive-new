package com.github.liyasharipova.blockchain.filestorage.api;

import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

public interface BlockApi {

    /**
     * Проставление номера блока в указанных файлах, чтобы знать об их местонахождении в сети блокчейн
     */
    @RequestMapping(value = "/set-blocks",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    void setBlocksPost(@Valid @RequestBody BlockRequest blockRequest);

    /**
     * Получить номер блока
     */
    @RequestMapping(value = "/receive-block-number",
                    method = RequestMethod.POST)
    ResponseEntity<UUID> receiveBlockNumberPost();
}
