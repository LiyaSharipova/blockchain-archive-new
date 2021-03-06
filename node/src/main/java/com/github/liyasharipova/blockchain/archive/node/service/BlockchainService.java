package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.application.api.dto.request.BlockRequest;
import com.github.liyasharipova.blockchain.archive.node.repository.TransactionRepository;
import com.github.liyasharipova.blockchain.archive.node.util.StringUtil;
import com.github.liyasharipova.blockchain.node.api.dto.request.NonceCheckRequest;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import com.github.liyasharipova.blockchain.node.api.dto.response.BlockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Сервис для работы с блокчейном
 */
@Service
@Slf4j
@Transactional
public class BlockchainService {

    @Value("${difficulty}")
    private int difficulty;

    @Value("#{'${node.hosts}'.split(',')}")
    private List<String> nodeHosts;

    @Value("#{'${node.ports}'.split(',')}")
    private List<String> nodePorts;

    @Value("${server.port}")
    private String ownPort;

    @Value("${server.address}")
    private String ownHost;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${application.host}")
    private String applicationHost;

    @Value("${application.port}")
    private String applicationPort;

    @Autowired
    private BlockService blockService;

    public void mineBlockAndPlaceToBlockchain(BlockDto block) {
        mineBlock(block);
        blockService.saveBlockIfNotExist(block);
    }

    /**
     * Увеличиваем значение nonce пока нужный хэш не будет найден
     */
    public BlockDto mineBlock(BlockDto block) {
        Random random = new Random();
        block.setPreviousHash(blockService.getLastBlockHash());
        block.setMerkleRoot(StringUtil.getMerkleRoot(block.getTransactions()));
        block.setHash(blockService.calculateHash(block));
        String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0"

        while (!block.getHash().substring(0, difficulty).equals(target) &&
                (!blockService.isThisBlockInSuccessfulBlocks(block))) {
            block.setNonce(random.nextLong());
            block.setHash(blockService.calculateHash(block));
        }
        // Если блок оказался среди замайненных блоков, то удаляем его из очереди
        if (blockService.isThisBlockInSuccessfulBlocks(block)) {
            return block;
        }
        sendMininfResultToOtherNodes(block);
        sendMiningResultToApplication(block);
        log.info("Block mined with hash {} ", block.getHash().substring(0, 6));
        return block;
    }

    private void sendMiningResultToApplication(BlockDto block) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://" + applicationHost + ":" + applicationPort + "/receive-mining-result";
        BlockRequest blockRequest = new BlockRequest();
        blockRequest.setBlockNumber(block.getNumber());
        blockRequest
                .setFileIds(block.getTransactions().stream().map(TransactionDto::getId).collect(Collectors.toList()));
        restTemplate.postForObject(uri, blockRequest, Void.class);
    }

    private void sendMininfResultToOtherNodes(BlockDto block) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < nodeHosts.size(); i++) {
            String nodeHostAndPort = nodeHosts.get(i) + nodePorts.get(i);
            if (!nodeHostAndPort.equals(ownHost + ownPort)) {
                String uri = "http://" + nodeHosts.get(i) + ":" + nodePorts.get(i) + "/receive-mined-block-info";
                NonceCheckRequest nonceCheckRequest = new NonceCheckRequest();
                nonceCheckRequest.setBlockHash(block.getHash());
                nonceCheckRequest.setNonce(block.getNonce());
                nonceCheckRequest.setTransactions(block.getTransactions());
                try {
                    restTemplate.postForObject(uri, nonceCheckRequest, Boolean.class);
                } catch (Exception e) {
                    log.error("Ошибка при отправке POST {},  {}, {}", uri, nonceCheckRequest.toString(), e.getMessage(), e.getCause());
                }
                log.info("Отправлен mined block info {}:{}", nodeHosts.get(i), nodePorts.get(i));
            }
        }
    }

}