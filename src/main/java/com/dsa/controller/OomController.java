package com.dsa.controller;

import com.dsa.service.OomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oom")
public class OomController {

    private final OomService oomService;

    public OomController(OomService oomService) {
        this.oomService = oomService;
    }

    @GetMapping
    public String trigger() {
        oomService.trigger();
        return "unreachable";
    }
}
