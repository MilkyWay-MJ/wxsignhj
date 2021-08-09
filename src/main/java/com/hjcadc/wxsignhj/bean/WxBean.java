package com.hjcadc.wxsignhj.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月03日 14:43
 * @Description: 微信appId,appSecret,位置服务key的属性注入
 */
@Component
@ConfigurationProperties(prefix = "wexin")
@Data
public class WxBean {

    private String appId;

    private String appSecret;
    
    private String key;


}
