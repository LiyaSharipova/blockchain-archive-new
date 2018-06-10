package com.github.liyasharipova.blockchain.archive.application.service;

import com.github.liyasharipova.blockchain.archive.application.dto.NonceRangeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Сервис для работы с nonce
 */
@Service
@Slf4j
@Transactional
public class NonceService {

    private int nonceStep = 15;

    /** Использованные шаги для генерации новых диапазоново nonce для каждого блока */
    private Map<Long, Long> usedNoncesRange = new ConcurrentHashMap<>();

    public NonceRangeDto calculateNonceRange(Integer nodeId, Long blockId) {

        Long nonceCounter = usedNoncesRange.computeIfAbsent(blockId, block -> 0L);

        // 0-15 при nonceStep = 15
        if (nonceCounter == 0) {
            usedNoncesRange.replace(blockId, nonceCounter + 1);
            return new NonceRangeDto(0L, new Long(nonceStep));
        }
        // 16-30, 31-45 при nonceStep = 15
        usedNoncesRange.replace(blockId, nonceCounter + 1);
        return new NonceRangeDto(nonceCounter * nonceStep + 1, (nonceCounter + 1) * nonceStep);
    }
}

// I. usedNoncesRange: 0 -> 0
// I. usedNoncesRange: 0 -> 1, 0-15

// II. usedNoncesRange: 0 -> 1
// II. usedNoncesRange: 0 -> 2
// II. usedNoncesRange: 0 -> 2

// III. usedNoncesRange: 1 -> 0
// III. usedNoncesRange: 0 -> 1, 0-15




