package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlocksQueue;
import com.github.liyasharipova.blockchain.archive.node.dto.FutureBlock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * Класс, который через определенный промежуток проверят наличие блоков
 * в очереди на майнинг и запускает их майнинг.
 * Запускается сразу после старта приложения, так как реализует {@link CommandLineRunner}
 */
@Service
@Slf4j
public class MinerLoop implements CommandLineRunner {

    @Value("block.queue.check.period")
    private Integer blockQueueCheckPeriod;

    private BlockchainService blockchainService;

    @Autowired
    public MinerLoop(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @Override
    public void run(String... strings) throws Exception {
        while (true) {
            Queue<FutureBlock> blocksQueue = BlocksQueue.getBlocksQueue();
            // Пока не замайнятся все блоки из очереди, доставать их по одному и майнить
            while (!blocksQueue.isEmpty()) {
                FutureBlock block = blocksQueue.peek();
                blockchainService.mineBlockAndPlaceToBlockchain(block);

                // todo здесь дождаться, чтобы все ноды сказали ок после своего майнинга и проверки nonce
                blocksQueue.remove();
                log.info("Замайнен блок: {}", block.getHashes());
            }
            Thread.sleep(blockQueueCheckPeriod * 1000);
        }
    }
}