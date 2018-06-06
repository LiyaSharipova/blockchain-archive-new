package com.github.liyasharipova.blockchain.archive.node.dto;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BlocksQueue {

    private static Queue<FutureBlock> blocksQueue = new ConcurrentLinkedQueue();

    public static Queue<FutureBlock> getBlocksQueue() {
        return blocksQueue;
    }

    private BlocksQueue() {
    }
}
