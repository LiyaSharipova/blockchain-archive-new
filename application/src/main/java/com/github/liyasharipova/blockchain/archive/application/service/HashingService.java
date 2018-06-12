package com.github.liyasharipova.blockchain.archive.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ubmb.jstribog.StribogProvider;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.Security;

/**
 * Сервис для работы с алгоритмом хеширования Стрибог
 */
@Service
@Slf4j
@Transactional
public class HashingService {

    private static final String STRIBOG_VERSION = "Stribog256";

    public String hash(byte[] fileData) {
        if (Security.getProvider("JStribog") == null) {
            Security.addProvider(new StribogProvider());
        }

        // Здесь происходит магия хеширования
        try {
            MessageDigest digest = MessageDigest.getInstance(STRIBOG_VERSION);

            //Applies hash() to our input,
            byte[] hash = digest.digest(fileData);

            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}