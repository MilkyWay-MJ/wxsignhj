package com.hjcadc.wxsignhj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjcadc.wxsignhj.bean.Users;
import org.apache.ibatis.annotations.Mapper;


/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月03日 19:54
 * @Description: 用户信息mapper接口
 */
@Mapper
public interface UserMapper extends BaseMapper<Users> {
}
