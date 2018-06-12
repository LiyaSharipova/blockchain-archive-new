package com.github.liyasharipova.blockchain.archive.node.dto;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BlocksQueue {

    private static Queue<BlockDto> blocksQueue = new ConcurrentLinkedQueue();

    public static Queue<BlockDto> getBlocksQueue() {
        return blocksQueue;
    }

    private BlocksQueue() {
    }
}
