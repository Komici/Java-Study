package com.app.api.client.user.dao;

import com.app.api.client.user.model.SysUser;
import com.app.api.client.user.model.SysUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * SysUserDAO继承基类
 */
@Mapper
public interface SysUserDAO extends MyBatisBaseDao<SysUser, String, SysUserExample> {
}