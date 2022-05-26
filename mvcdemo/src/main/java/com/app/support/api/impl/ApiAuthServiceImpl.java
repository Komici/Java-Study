package com.app.support.api.impl;

import com.app.support.api.ApiAuthService;
import com.app.support.api.request.ApiLoginUser;
import com.app.support.view.AjaxResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiAuthServiceImpl implements ApiAuthService {

    private String secret = "JWT@2022@komici@mvcdemo";

    public String createToken(Integer tokenExpires, String userId, Integer userType,  String phoneNumber){
        if(tokenExpires == null)tokenExpires = 7 * 24;

        //生成JWT
        Map<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, tokenExpires);
        String token = JWT.create()
                .withHeader(map)//添加头部
                .withClaim("userId", userId)
                .withClaim("userType", userType)
                .withClaim("phoneNumber", phoneNumber)
                .withExpiresAt(instance.getTime())//设置过期时间
                .sign(Algorithm.HMAC256(secret));//设置签名 密钥
        return token;
    }
    public AjaxResult checkToken(String token){
        try {
            //创建验证对象,这里使用的加密算法和密钥必须与生成TOKEN时的相同否则无法验证
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            //验证JWT
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            //获取JWT中的数据,注意数据类型一定要与添加进去的数据类型一致,否则取不到数据
//            String platformByJWT = decodedJWT.getClaim("platform").asString();
//            if(StringUtils.isBlank(platform) || !platform.equals(platformByJWT)){
//                return AjaxResult.wrapError("身份令牌无权限请求该接口");
//            }
            Long userId = decodedJWT.getClaim("userId").asLong();
            Integer userType = decodedJWT.getClaim("userType").asInt();
            String realName = decodedJWT.getClaim("realName").asString();

            ApiLoginUser apiLoginUser = new ApiLoginUser();
            apiLoginUser.setId(userId);
            apiLoginUser.setUserType(userType);
            apiLoginUser.setRealName(realName);
            return AjaxResult.wrapAjaxResult(true, apiLoginUser);
        } catch (SignatureVerificationException e){
            return AjaxResult.wrapError("身份令牌无效");
        } catch (Exception e){
            return AjaxResult.wrapError("身份令牌无效");
        }
    }

}