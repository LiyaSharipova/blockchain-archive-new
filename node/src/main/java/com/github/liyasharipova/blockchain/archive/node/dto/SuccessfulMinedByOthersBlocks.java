package com.github.liyasharipova.blockchain.archive.node.dto;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class SuccessfulMinedByOthersBlocks {

    private static List<BlockDto> successfulBlocks = new CopyOnWriteArrayList();

    public static List<BlockDto> getSuccessfulBlocks() {
        return successfulBlocks;
    }

    private SuccessfulMinedByOthersBlocks() {
    }
}