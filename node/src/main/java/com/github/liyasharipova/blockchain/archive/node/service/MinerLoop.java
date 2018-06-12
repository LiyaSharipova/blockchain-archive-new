package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.BlocksQueue;
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

    @Value("${block.queue.check.period}")
    private Integer blockQueueCheckPeriod;

    private BlockchainService blockchainService;

    private BlockService blockService;

    @Autowired
    public MinerLoop(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @Override
    public void run(String... strings) throws Exception {
        while (true) {
            Queue<BlockDto> blocksQueue = BlocksQueue.getBlocksQueue();
            // Пока не замайнятся все блоки из очереди, доставать их по одному и майнить
            //todo поставить здесь условие, что нужно не майнит те блоки,
            // которые уже были замайнены в других нодах.
            // Информация об этом должна к нам прийти
            BlockDto block = null;

            while (!blocksQueue.isEmpty() && (!blockService.isThisBlockInSuccessfulBlocks(blocksQueue.peek()))) {
                blockchainService.mineBlockAndPlaceToBlockchain(blocksQueue.peek());

                // todo здесь дождаться, чтобы все ноды сказали ок после своего майнинга и проверки nonce
                // todo но это нужно делать не здесь,  чтобы майнинг продолжался
                blocksQueue.remove();
                log.info("Замайнен блок: {}", block.getTransactions());
            }
            Thread.sleep(blockQueueCheckPeriod * 1000);
        }
    }

}