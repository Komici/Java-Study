package com.app.support.view;

import com.app.support.utils.StringUtil;
import com.app.support.utils.json.JsonUtil;

import java.util.Date;

public class AjaxResult<T> {
    public static final String SUCCESS = "200";
    public static final String FAILURE = "0";
    private String returnCode;
    private String msg = "";
    private T data;
    private String returnTime;

    public AjaxResult() {
    }

    public AjaxResult(boolean success) {
        this.setReturnCode(success);
        this.setReturnTime();
    }

    public AjaxResult(boolean success, String msg) {
        this.setReturnCode(success);
        this.setMsg(msg);
        this.setReturnTime();
    }

    public AjaxResult(boolean success, T data) {
        this.setReturnCode(success);
        this.setData(data);
        this.setReturnTime();
    }

    public AjaxResult(boolean success, String msg, T data) {
        this.setReturnCode(success);
        this.setMsg(msg);
        this.setData(data);
        this.setReturnTime();
    }

    public boolean success() {
        return "200".equals(this.returnCode);
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public void setReturnCode(boolean success) {
        if (success) {
            this.returnCode = "200";
        } else {
            this.returnCode = "0";
        }

    }

    public String getMsg() {
        if (this.msg == null || "".equals(this.msg)) {
            if ("200".equals(this.returnCode)) {
                this.msg = "操作成功";
            } else {
                this.msg = "操作失败";
            }
        }

        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getReturnTime() {
        if (this.returnTime == null) {
            this.setReturnTime();
        }

        return this.returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public void setReturnTime() {
        this.returnTime = (new Date()).getTime() + "";
    }

    public static AjaxResult wrapSuccess(String msg) {
        return new AjaxResult(true, msg);
    }

    public static AjaxResult wrapError(String msg) {
        return new AjaxResult(false, msg);
    }

    public static AjaxResult wrapAjaxResult(String returnCode, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setReturnCode(returnCode);
        ajaxResult.setMsg(msg);
        return ajaxResult;
    }

    public static AjaxResult wrapAjaxResult(boolean success) {
        return new AjaxResult(success);
    }

    public static <T> AjaxResult wrapAjaxResult(boolean success, T data) {
        return new AjaxResult(success, data);
    }

}
