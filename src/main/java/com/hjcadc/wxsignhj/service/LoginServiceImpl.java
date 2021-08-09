package com.hjcadc.wxsignhj.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjcadc.wxsignhj.bean.Users;
import com.hjcadc.wxsignhj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月03日 17:29
 * @Description: 登录模块服务层实现类
 */
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, Users> implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Users> selectByOpenid(String openid) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("openid").allEq(new HashMap<String, Object>() {{
            put("openid", openid);
        }});
        List<Users> users = userMapper.selectList(queryWrapper);
        return users;
    }


}
