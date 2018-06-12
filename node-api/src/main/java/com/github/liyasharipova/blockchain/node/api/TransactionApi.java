package com.github.liyasharipova.blockchain.node.api;

import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface TransactionApi {

    /**
     * Получить хэш и id файла для добавления его в блок
     *
     * @param transaction
    //TODO  подумать зачем
     * @return id файла
     */
    @RequestMapping(value = "/receive-file-info",
                    consumes = {"application/json"},
                    method = RequestMethod.POST)
    void receiveHash(@Valid @RequestBody TransactionDto transaction);
}
