package com.hjcadc.wxsignhj.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月03日 15:47
 * @Description: 微信用户信息
 */
@Data
public class Users implements Serializable {

    private String id;

    // 微信openid
    private String openid;

    // 手机号
    private String phone;

    // 性别
    private String sex;

    // 签到人姓名
    private String nickName;

    // 签到时间
    private String signTime;
}
