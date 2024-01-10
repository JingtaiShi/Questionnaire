package com.sjt.web.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjt.web.user.entity.SysUser;
import com.sjt.web.user.mapper.SysUserMapper;
import com.sjt.web.user.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author Rocky
 * @create 2022-01-1315:36
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
}
