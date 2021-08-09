package com.hjcadc.wxsignhj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjcadc.wxsignhj.bean.Users;

import java.util.List;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月03日 17:26
 * @Description: 登录模块服务层接口
 */
public interface LoginService extends IService<Users> {

    /**
     * 根据微信openid查询签到信息
     * @param openid 微信openid
     * @return: java.util.List<com.hjcadc.wxsignhj.bean.Users>
     * @Author: 莫京 2021/8/4
    */
    List<Users> selectByOpenid(String openid);
}
