package com.app.support.api;


import com.app.support.view.AjaxResult;

public interface ApiAuthService {



    String createToken(Integer tokenExpires, String userId, Integer userType, String phoneNumber);

    AjaxResult checkToken(String token);

}
