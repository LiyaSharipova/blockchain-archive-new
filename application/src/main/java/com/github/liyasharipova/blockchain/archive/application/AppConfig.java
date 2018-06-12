package com.github.liyasharipova.blockchain.archive.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("app")
@Configuration
@ComponentScan(basePackages = {"com.github.liyasharipova.blockchain.archive.application"})
public class AppConfig {

}