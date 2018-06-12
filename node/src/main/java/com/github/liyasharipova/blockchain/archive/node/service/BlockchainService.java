package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.application.api.dto.request.NonceRequest;
import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.NonceRangeDto;
import com.github.liyasharipova.blockchain.archive.node.repository.BlockRepository;
import com.github.liyasharipova.blockchain.archive.node.repository.TransactionRepository;
import com.github.liyasharipova.blockchain.archive.node.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с блокчейном
 */
@Service
@Slf4j
@Transactional
public class BlockchainService {

    @Value("${difficulty}")
    private int difficulty;

    //todo убрать, так как хранилище только одно -- БД
    private List<BlockDto> blockchain = new ArrayList<>();

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${application.host}")
    private String applicationHost;

    @Value("${application.port}")
    private String applicationPort;

    @Autowired
    private BlockService blockService;

    public void mineBlockAndPlaceToBlockchain(BlockDto block) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + applicationHost + ":" + applicationPort + "/nonces";
        NonceRequest request = new NonceRequest(block.getUuid());
        NonceRangeDto nonceRange = restTemplate.postForObject(url, request, NonceRangeDto.class);
        mineBlock(block, nonceRange);
        blockchain.add(block);
    }

    //    /**
    //     * todo Нужно замайнить блок, потом положить его в блокчейн
    //     *
    //     * @param block
    //     */
    //    private void mineBlock(FutureBlock block) {
    //    }

    /**
     * Увеличиваем значение nonce пока нужный хэш не будет найден
     */
    public void mineBlock(BlockDto block, NonceRangeDto nonceRange) {
        block.setPreviousHash(getLastBlockHash());
        block.setNonce(nonceRange.getBeginNonce());
        block.setMerkleRoot(StringUtil.getMerkleRoot(block.getTransactions()));
        String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0"

        while (!block.getHash().substring(0, difficulty).equals(target)
                && !block.getNonce().equals(nonceRange.getEndNonce()) &&
                (!blockService.isThisBlockInSuccessfulBlocks(block))) {
            block.increaseNonce();
            block.setHash(blockService.calculateHash(block));
        }

        //todo если майнинг будет неуспешным, то нужно  будет запросить вновь NonceRangeDto
        log.info("Block mined with hash {} ", block.getHash().substring(0, 6));
    }

    public String getLastBlockHash() {
        if (blockchain.size() == 0) {
            return "0";
        }
        return blockchain.get(blockchain.size() - 1).getHash();
    }

    public void addToBlockChain(BlockDto blockDto) {
        blockchain.add(blockDto);
    }

    public Long getLastBlockNumber() {
        return Long.valueOf(blockchain.size());
    }

}