package com.app.support.api.filter;

import com.app.context.AppContextUtil;
import com.app.support.api.ApiAuthService;
import com.app.support.api.request.ApiLoginUser;
import com.app.support.constant.ApiConstant;
import com.app.support.utils.ApiExcludeRegisterCenter;
import com.app.support.utils.request.ApiHttpServletRequest;
import com.app.support.utils.request.ResponseUtils;
import com.app.support.view.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
@Configuration
public class AuthFilter implements Filter {
    private String loginUri = "/login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        HttpServletResponse response = (HttpServletResponse) sresponse;

        if(request.getMethod().equals("OPTIONS")){
            chain.doFilter(srequest, sresponse);
        }
        else{
            String uri = request.getRequestURI();
            String Authorization = request.getHeader(ApiConstant.HEADER_AUTHORIZATION);
            if (null == loginUri && uri.indexOf(loginUri) > -1)
            {
                chain.doFilter(srequest, sresponse);
            } else if (uri.indexOf("/api/") > -1)
            {
                if (ApiExcludeRegisterCenter.isExcluded(uri))
                {
                    chain.doFilter(srequest, sresponse);
                } else {
                    //身份认证
                    AjaxResult<ApiLoginUser> result = this.AuthLoginUser(Authorization, "1");
                    if (result.success()) {
                        ApiLoginUser apiLoginUser = result.getData();
                        request.setAttribute("apiLoginUser", apiLoginUser);
                        chain.doFilter(new ApiHttpServletRequest(request, apiLoginUser.getId()), sresponse);
                    } else {
                        ResponseUtils.writeJson(response, result);
                    }
                }
            } else if (uri.indexOf("/web/") > -1 || uri.contains("swagger") || uri.contains("/csrf") || uri.contains("api-doc")) {//web请求，全部放行
                chain.doFilter(srequest, sresponse);
            } else if (uri.endsWith("/")){
                PrintWriter writer = null;
                try {
                    writer = sresponse.getWriter();
                    writer.write("Api Service");
                    writer.flush();
                } finally {
                    if (writer != null) {
                        writer.close();
                    }
                }
            } else {
                if (uri.indexOf("/static/") > -1 || uri.indexOf("/templates/") > -1) {
                    chain.doFilter(srequest, sresponse);
                } else {
                    String loginUser = null;
                    if (loginUser == null) {
                        response.sendRedirect(request.getContextPath() + "/login");
                    } else {
                        chain.doFilter(srequest, sresponse);
                    }
                }
            }
        }
    }

    private AjaxResult<ApiLoginUser> AuthLoginUser(String Authorization, String Platform) {
        AjaxResult<ApiLoginUser> result = AjaxResult.wrapAjaxResult(false);
        if (StringUtils.isNotBlank(Authorization)) {
            ApiAuthService apiAuthService = AppContextUtil.getBean(ApiAuthService.class);
            result = apiAuthService.checkToken(Authorization);
            if (!result.success()) {
                result.setReturnCode(ApiConstant.ID_AUTH_FAILURE);
            }
        } else {
            result.setReturnCode(ApiConstant.ID_AUTH_FAILURE);
            result.setMsg("请在请求头中传入Authorization参数");
        }
        return result;
    }

    @Override
    public void destroy() {

    }
}
