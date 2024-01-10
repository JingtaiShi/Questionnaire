package com.sjt.web.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户实体类
 * @author Rocky
 * @create 2022-01-1315:16
 */
@Data
@TableName("sys_user")//指定SysUser对应的数据库表名 sys_user
public class SysUser implements Serializable {
    //主键
    @TableId(type = IdType.AUTO)//指明主键，自动递增
    private Long userId;
    //登陆账号
    private String username;
    //登陆密码
    private String password;
    //电话
    private String phone;
    //邮箱
    private String email;
}
