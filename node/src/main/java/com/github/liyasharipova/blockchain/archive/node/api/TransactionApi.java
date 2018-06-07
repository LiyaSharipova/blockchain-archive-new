package com.github.liyasharipova.blockchain.archive.node.api;

import com.github.liyasharipova.blockchain.archive.node.dto.request.Transaction;
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
    Long receiveHash(@Valid @RequestBody Transaction transaction);
}
