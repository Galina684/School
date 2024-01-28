package ru.hogwarts.scool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.scool.controller.InfoController;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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

    public int calculate() {
        logger.info("Был вызван метод calculate");
        long start = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        long finish = System.currentTimeMillis();
        logger.info("Время вычисления: " + (finish - start));
        return sum;
    }

}
