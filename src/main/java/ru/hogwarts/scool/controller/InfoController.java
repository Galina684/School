package ru.hogwarts.scool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.scool.service.InfoServiceInterface;


@RestController
@RequestMapping("/info")
public class InfoController {
    private final InfoServiceInterface info;

    public InfoController(InfoServiceInterface info) {
        this.info = info;
    }

    @GetMapping
    Integer infoPort() {
        return info.portInfo();
    }
}
