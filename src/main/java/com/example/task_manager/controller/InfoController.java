package com.example.task_manager.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.max-tasks-per-page}")
    private int maxTasksPerPage;

    @GetMapping
    public Map<String, String> getInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("appName", appName);
        map.put("appVersion", appVersion);
        map.put("maxTasksPerPage", String.valueOf(maxTasksPerPage));
        return map;
    }


}
