package com.sjt.web.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjt.utils.ResultUtils;
import com.sjt.utils.ResultVo;
import com.sjt.web.user.entity.SysUser;
import com.sjt.web.user.entity.UserParm;
import com.sjt.web.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理模块控制器
 * @author Rocky
 * @create 2022-01-1315:42
 */
@RestController//Controller&ResponseBody
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody SysUser sysUser){
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername,sysUser.getUsername())
                .eq(SysUser::getPassword,sysUser.getPassword());
        SysUser one = sysUserService.getOne(query);
        if(one == null){
            return ResultUtils.error("用户名或密码错误");
        }
        return ResultUtils.success("登录成功",one.getUserId());
    }
    /**
     * 获取用户信息
     */
    @GetMapping("/getInfo")
    public ResultVo getInfo(Long userId){
        SysUser user = sysUserService.getById(userId);
        return ResultUtils.success("查询成功",user.getUsername());
    }
    /**
     * 新增
     */
    @PostMapping
    public ResultVo add(@RequestBody SysUser sysUser){ //@ResponseBody
            boolean save = sysUserService.save(sysUser);
            if(save){
            return ResultUtils.success("新增用户成功!");
        }
        return ResultUtils.error("新增用户失败!");
    }

    /**
     * 编辑
     */
    @PutMapping
    public ResultVo edit(@RequestBody SysUser sysUser){
        boolean b = sysUserService.updateById(sysUser);
        if(b){
            return ResultUtils.success("编辑用户成功!");
        }
        return ResultUtils.error("编辑用户失败!");
    }

    /**
     * 删除
     */
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId){
        boolean b = sysUserService.removeById(userId);
        if(b){
            return ResultUtils.success("删除用户成功!");
        }
        return ResultUtils.error("删除用户失败!");
    }

    /**
     * 列表查询
     */
    @GetMapping("/list")
    public ResultVo getList(UserParm userParm){
        //构造分页对象
        IPage<SysUser> page = new Page<>();
        page.setCurrent(userParm.getCurrentPage());
        page.setSize(userParm.getPageSize());
        //构造查询条件
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().like(SysUser::getUsername, userParm.getUsername());
        IPage<SysUser> list = sysUserService.page(page, query);
        return ResultUtils.success("查询成功",list);
    }
}
