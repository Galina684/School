package ru.hogwarts.scool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.scool.service.InfoService;
import ru.hogwarts.scool.service.InfoServiceInterface;


@RestController
@RequestMapping("/info")
public class InfoController {
    private final InfoServiceInterface info;
    private InfoService infoService;

    public InfoController(InfoServiceInterface info, InfoService infoService) {
        this.info = info;
        this.infoService = infoService;
    }

    @GetMapping
    Integer infoPort() {
        return info.portInfo();
    }

    @GetMapping("/calculate")
    public int calculate() {
        return infoService.calculate();
    }

}
