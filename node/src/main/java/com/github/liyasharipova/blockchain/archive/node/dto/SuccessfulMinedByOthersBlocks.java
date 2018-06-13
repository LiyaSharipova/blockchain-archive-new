package com.github.liyasharipova.blockchain.archive.node.dto;

import com.github.liyasharipova.blockchain.node.api.dto.response.BlockDto;

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