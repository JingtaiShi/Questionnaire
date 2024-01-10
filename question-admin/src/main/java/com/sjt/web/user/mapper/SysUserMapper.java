package com.sjt.web.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjt.web.user.entity.SysUser;

/**
 * 用户的数据访问层
 * 继承mybatis plus的BaseMapper，可以使用他提供的通用的增删改查的方法
 * @author Rocky
 * @create 2022-01-1315:23
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
}
