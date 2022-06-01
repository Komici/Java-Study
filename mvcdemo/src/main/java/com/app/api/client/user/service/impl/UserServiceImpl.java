package com.app.api.client.user.service.impl;

import com.app.api.client.user.dao.SysUserDAO;
import com.app.api.client.user.model.SysUser;
import com.app.api.client.user.model.SysUserExample;
import com.app.api.client.user.service.UserService;
import com.app.support.utils.MapUtil;
import com.app.support.utils.RedisUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    SysUserDAO sysUserDAO;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RedisUtil redisUtil;
    @Override
    public Boolean exist(String userName,String password){
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(userName).andPasswordEqualTo(password);
        try
        {
            String key = new StringBuilder(SysUser.class.getSimpleName()).append(":"+userName).toString();

            Map<Object, Object>  fromMap = redisUtil.hGetAll(key);
            if(fromMap != null && fromMap.size() > 0)
            {
                SysUser fromUser = MapUtil.mapToBean(fromMap,new SysUser());
                return true;
            }

            List<SysUser> list = sysUserDAO.selectByExample(example);
            if(null != list && list.size() > 0)
            {

                Map<String, String> map = MapUtil.beanToMap(list.get(0));
                redisUtil.hPutAll(key, map);
                redisUtil.expire(key, 7 * 24 * 60, TimeUnit.MINUTES);
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
