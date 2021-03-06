package com.github.liyasharipova.blockchain.archive.node.service;


import com.github.liyasharipova.blockchain.node.api.dto.response.BlockDto;
import com.github.liyasharipova.blockchain.node.api.dto.response.SelfCheckResultDto;
import com.github.liyasharipova.blockchain.archive.node.entity.BlockEntity;
import com.github.liyasharipova.blockchain.archive.node.repository.BlockRepository;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class NodeService {

    @Value("${difficulty}")
    private int difficulty;

    @Autowired
    BlockService blockService;

    @Autowired
    BlockRepository blockRepository;

    public SelfCheckResultDto selfCheck() {
        List<BlockEntity> allBlocks = blockRepository.findAll();
        return selfCheck((long) allBlocks.size());
    }

    public SelfCheckResultDto selfCheck(Long blockNumber) {
        List<BlockDto> blockDtos = blockService.getAllBlockDtos();
        BlockDto currentBlock;
        BlockDto previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        //loop through blockchain to check hashes:
        SelfCheckResultDto checkResultDto = new SelfCheckResultDto();
        for (int i = 1; i < blockNumber; i++) {

            currentBlock = blockDtos.get(i);
            previousBlock = blockDtos.get(i - 1);
            //compare registered hash and calculated hash:
            //compare previous hash and registered previous hash
            //check if hash is solved
            if (!currentBlock.getHash().equals(blockService.calculateHash(currentBlock)) ||
                    !previousBlock.getHash().equals(currentBlock.getPreviousHash()) ||
                    !currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                checkResultDto.setBlockNumber((long) i);
                checkResultDto.setIsCheckSuccessful(false);
                checkResultDto.setLength((long) 0);
                return checkResultDto;
            }
        }
        checkResultDto.setIsCheckSuccessful(true);
        checkResultDto.setLength((long) blockDtos.size());
        return checkResultDto;
    }


}
