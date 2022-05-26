package com.app.support.api.request;

public class ApiLoginUser {
    private Long id;
    private String realName;
    private String username;
    private Integer userType;

    public ApiLoginUser() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }


    public String toString() {
        return "ApiLoginUser{id=" + this.id + ", realName='" + this.realName + '\'' + ", username='" + this.username + '\'' + ", userType=" + this.userType +  '}';
    }
}
