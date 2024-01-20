package ru.hogwarts.scool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.scool.controller.InfoController;

@Service
@Profile("!info")
public class InfoService implements InfoServiceInterface {
    @Value("8080")
    Integer port;
    private final Logger logger = LoggerFactory.getLogger(InfoService.class);
    @Override
    public Integer portInfo() {
        logger.info("Порт подключения " + port);
        return port;
    }
}
