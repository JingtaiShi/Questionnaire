package com.sjt.web.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Rocky
 * @create 2022-01-1315:57
 */
@Data
public class UserParm implements Serializable {
    //当前第几页
    private Long currentPage;
    //页容量
    private Long pageSize;
    private String username;
}
