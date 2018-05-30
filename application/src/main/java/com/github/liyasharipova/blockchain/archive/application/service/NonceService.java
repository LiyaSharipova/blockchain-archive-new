package com.github.liyasharipova.blockchain.archive.application.service;

import com.github.liyasharipova.blockchain.archive.application.dto.NonceRangeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("nonce.step")
    private Long nonceStep;

    /** Использованные шаги для генерации новых диапазоново nonce для каждого блока */
    private Map<Long, Long> usedNoncesRange = new ConcurrentHashMap<>();

    public NonceRangeDto calculateNonceRange(Integer nodeId, Long blockId) {

        Long nonceCounter = usedNoncesRange.computeIfAbsent(blockId, block -> 0L);

        // 0-15 при nonceStep = 15
        if (nonceCounter == 0) {
            return new NonceRangeDto(0L, nonceStep);
        }
        // 16-30, 31-45 при nonceStep = 15
        return new NonceRangeDto(nonceCounter * nonceStep + 1, (nonceCounter + 1) * nonceStep);
    }
}