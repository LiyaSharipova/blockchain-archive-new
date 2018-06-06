package com.github.liyasharipova.blockchain.archive.node.controller;

import com.github.liyasharipova.blockchain.archive.node.api.TransactionApi;
import io.swagger.model.Hash;
import io.swagger.model.InlineResponse200;
import org.springframework.http.ResponseEntity;

/**
 *
 */
public class TransactionController implements TransactionApi{

    @Override
    public Long receiveHash(Hash body) {
        return null;
    }
}