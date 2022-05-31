package com.app.api.client.user.service.impl;

import com.app.api.client.user.dao.SysUserDAO;
import com.app.api.client.user.model.SysUser;
import com.app.api.client.user.model.SysUserExample;
import com.app.api.client.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    SysUserDAO sysUserDAO;

    @Override
    public Boolean exist(String userName,String password){
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(userName).andPasswordEqualTo(password);
        try
        {
            List<SysUser> list = sysUserDAO.selectByExample(example);
            if(null != list && list.size() > 0)
            {
                return true;
            }
        }
        catch (Exception ex)
        {
            int i= 2;
        }

        return false;
    }
}
