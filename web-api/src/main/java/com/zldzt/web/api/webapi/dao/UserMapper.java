package com.zldzt.web.api.webapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zldzt.web.api.webapi.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
