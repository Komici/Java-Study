package com.app.api.client.user.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/v1")
public class dashboardController {

    @GetMapping("dashboard")
    public String index(){
        return "dashboard";
    }
}
