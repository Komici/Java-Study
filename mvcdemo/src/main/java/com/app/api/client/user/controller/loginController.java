package com.app.api.client.user.controller;

import com.app.api.client.user.service.UserService;
import com.app.support.api.ApiAuthService;
import com.app.support.utils.ApiExcludeRegisterCenter;
import com.app.support.view.AjaxResult;
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

    @Resource
    UserService userService;
    @GetMapping("login")
    public AjaxResult<String> login(String userName, String password){
        Boolean exist =  userService.exist(userName,password);
        if(!exist)
        {
            return AjaxResult.wrapError("用户名或密码不正确");
        }
        String token = apiAuthService.createToken(null, UUID.randomUUID().toString(),1,"15888888888");
        return AjaxResult.wrapAjaxResult(true,token);
    }
}
