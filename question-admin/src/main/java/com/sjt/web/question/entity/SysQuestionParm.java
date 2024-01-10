package com.sjt.web.question.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Rocky
 * @create 2022-02-0410:50
 */
@Data
public class SysQuestionParm implements Serializable {
    //当前页
    private Long currentPage;
    //页容量
    private Long pageSize;
    //问卷标题
    private String questionTitle;
}
