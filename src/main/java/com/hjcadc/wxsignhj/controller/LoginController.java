package com.hjcadc.wxsignhj.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjcadc.wxsignhj.bean.Users;
import com.hjcadc.wxsignhj.bean.WxBean;
import com.hjcadc.wxsignhj.service.LoginService;
import com.hjcadc.wxsignhj.utils.HttpUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月03日 13:01
 * @Description: 微信签到控制层
 */
@RestController
public class LoginController {

    @Autowired
    private WxBean wxBean;

    @Autowired
    private LoginService loginService;

    /**
     * 登录凭证校验,用code换取openid
     *
     * @param code 微信小程序登录成功的code
     * @param from 小程序appid(后台已存有，可以不用这个)
     * @return: java.util.Map
     * @Author: 莫京 2021/8/5
     */
    @GetMapping("/code2openid")
    public Map code2openid(String code, String from) {
        Map data = new HashMap();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                wxBean.getAppId() + "&secret=" + wxBean.getAppSecret() + "&js_code=" + code + "&grant_type=authorization_code";
        String info = HttpUtils.doHttpPost(url, null, null, null, 5);
        Map<String, String> httpInfo = (Map<String, String>) JSON.parse(info);
        // 获取openid失败
        if (httpInfo.get("openid") == null) {
            data.put("msg", "获取openid失败");
        } else {
            data.put("openid", httpInfo.get("openid"));
        }
        data.put("data", httpInfo);
        return data;
    }

    /**
     * 点击"立即签到"按钮,新增签到信息
     *
     * @param openid   微信openid
     * @param nickName 输入的姓名
     * @param phone    输入的手机号
     * @return: java.util.Map
     * @Author: 莫京 2021/8/5
     */
    @GetMapping("/registerByOpenid")
    public Map registerByOpenid(String openid, String nickName, String phone) {
        Map data = new HashMap();
        // 获取openid失败
        if (StringUtils.isEmpty(openid)) {
            data.put("msg", "获取openid失败");
        } else {
            // 查询是否已签到过
            QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>().eq("openid", openid).last("LIMIT 1");
            Users one = loginService.getOne(queryWrapper);
            if (one != null) {
                data.put("is_registered", true);
                return data;
            }
            Users users = new Users();
            users.setOpenid(openid);
            users.setPhone(phone);
            users.setSex("0");
            users.setNickName(nickName);
            users.setSignTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            // 数据库插入用户数据
            boolean save = loginService.save(users);
            data.put("is_registered", false);
            if (!save) {
                data.put("msg", "数据保存失败");
                data.put("is_registered", true);
            }
            data.put("user", users);
        }
        return data;
    }

    /**
     * 根据openid获取签到信息
     *
     * @param openid   微信openid
     * @return: java.util.Map
     * @Author: 莫京 2021/8/5
     */
    @GetMapping("/getByOpenid")
    public Map getByOpenid(String openid) {
        Map data = new HashMap();
        if (StringUtils.isEmpty(openid)) {
            data.put("msg", "获取openid失败");
            return data;
        }
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>().eq("openid", openid).last("LIMIT 1");
        Users one = loginService.getOne(queryWrapper);
        data.put("user", one);
        return data;
    }
}
