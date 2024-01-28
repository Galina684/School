package ru.hogwarts.scool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("info")
public class InfoServiceTwo implements InfoServiceInterface{
    @Value("8081")
    Integer port;
    private final Logger logger = LoggerFactory.getLogger(InfoServiceTwo.class);
    @Override
    public Integer portInfo() {
        logger.info("Порт подключения " + port);
        return port;
    }
}
