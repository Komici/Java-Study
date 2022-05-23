package com.app.api.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/login/v1")
public class loginController {

    @GetMapping("login")
    public String login(){

        return "hello world";
    }
}
