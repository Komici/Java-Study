package com.app.api.client.controller;

import com.app.support.api.ApiAuthService;
import com.app.support.utils.ApiExcludeRegisterCenter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/api/client/v1")
public class loginController {
    static {
        ApiExcludeRegisterCenter.register("/api/client/v1");
    }

    @Resource
    ApiAuthService apiAuthService;
    @GetMapping("login")
    public String login(){
        String token = apiAuthService.createToken(null, UUID.randomUUID().toString(),1,"15888888888");
        return token;
    }
}
