package com.github.liyasharipova.blockchain.archive.node.exception;

/**
 * Исключение, возникающее во время неправильности цепочки
 */
public class ChainValidityException extends IllegalStateException {

    public ChainValidityException(String s) {
        super(s);
    }
}