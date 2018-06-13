package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.node.api.dto.response.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.BlocksQueue;
import com.github.liyasharipova.blockchain.node.api.dto.response.SelfCheckResultDto;
import com.github.liyasharipova.blockchain.archive.node.util.StringUtil;
import com.github.liyasharipova.blockchain.node.api.dto.request.NonceCheckRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

/**
 *
 */
@Service
@Transactional
@Slf4j
public class MiningResultCheckerService {

    private BlockService blockService;
    private NodeService nodeService;
    private BlockchainService blockchainService;

    @Value("#{'${node.hosts}'.split(',')}")
    private List<String> nodeHosts;

    @Value("#{'${node.ports}'.split(',')}")
    private List<String> nodePorts;

    @Value("${server.port}")
    private String ownPort;

    @Value("${server.address}")
    private String ownHost;

    @Autowired
    private StopMiningService stopMiningService;

    @Autowired
    public MiningResultCheckerService(
            BlockService blockService, NodeService nodeService, BlockchainService blockchainService) {
        this.blockService = blockService;
        this.nodeService = nodeService;
        this.blockchainService = blockchainService;
    }

    public Boolean checkMinedBlockInfo(NonceCheckRequest nonceCheckRequest) {

        BlockDto checkBlock = createCheckBlock(nonceCheckRequest);
        String calculatedHash = blockService.calculateHash(checkBlock);
        // Если не совпали, то просим остальные ноды и себя запустить selfcheck()
        if (!nonceCheckRequest.getBlockHash().equals(calculatedHash)) {
            SelfCheckResultDto checkResultDto = nodeService.selfCheck();
            RestTemplate restTemplate = new RestTemplate();
            // Отправка хеша каждой ноде
            if (checkResultDto.getIsCheckSuccessful()) {
                askOthersForCheck();
            } else {
                askToCopyRightBlocks(checkResultDto);
            }
        } else {
            //А если все нормально, остановить майнинг и добавить в блокчейн замайненный блок
//            todo остановить майнинг
//            todo удалить блок из очереди на майнинг
            blockService.saveBlock(checkBlock);
            stopMiningService.stopMining(checkBlock);
            return true;
        }

        //todo проверить, что транзакции те же самые -- но зачем, пока не ясно
        return null;
    }

    private BlockDto createCheckBlock(NonceCheckRequest nonceCheckRequest) {
        BlockDto lastBlock = BlocksQueue.getBlocksQueue().peek();
        BlockDto checkBlock = new BlockDto();
        checkBlock.setPreviousHash(blockService.getLastBlockHash());
        checkBlock.setTransactions(lastBlock.getTransactions());
        checkBlock.setMerkleRoot(StringUtil.getMerkleRoot(checkBlock.getTransactions()));
        checkBlock.setNonce(nonceCheckRequest.getNonce());
        return checkBlock;
    }

    private void askOthersForCheck() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < nodeHosts.size(); i++) {
            if (!nodeHosts.get(i).equals(ownHost)) {
                String uri = "http://" + nodeHosts.get(i) + ":" + nodePorts.get(i) + "/self-check";
                restTemplate.getForObject(uri, Long.class);
                log.info("Отправлен запрос на проверку {}:{}", nodeHosts.get(i), nodePorts.get(i));
            }
        }
    }

    private void askToCopyRightBlocks(SelfCheckResultDto resultDto) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> lengthPerHostAndPort = new HashMap<>();
        String uri;
        for (int i = 0; i < nodeHosts.size(); i++) {
            if (!nodeHosts.get(i).equals(ownHost)) {
                uri = "http://" + nodeHosts.get(i) + ":" + nodePorts.get(i) + "/self-check";
                Long length = restTemplate.getForObject(uri, SelfCheckResultDto.class).getLength();
                lengthPerHostAndPort.put(
                        nodeHosts.get(i) + ":" + nodePorts.get(i),
                        length);
                log.info("Отправлен запрос на проверку {}:{}", nodeHosts.get(i), nodePorts.get(i));
            }
        }
        Map.Entry<String, Long> hostAndPortWithMaxLength = lengthPerHostAndPort.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get();
        uri = "http://" + hostAndPortWithMaxLength.getKey() + "/copy-blocks";
        ResponseEntity<List<BlockDto>> blocksToCopyEntity =
                restTemplate.exchange(uri,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<BlockDto>>() {
                        });

        List<BlockDto> blocksToCopy = blocksToCopyEntity.getBody();
        blockService.reWriteBlocks(blocksToCopy);
    }

}