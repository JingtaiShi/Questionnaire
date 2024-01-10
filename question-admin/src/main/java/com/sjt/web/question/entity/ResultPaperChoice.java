package com.sjt.web.question.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultPaperChoice implements Serializable {
    private Long chioceId;
    //试题id
    private Long paperId;
    //选项标题
    private String choiceText;
    //序号
    private Integer choiceOrder;
    //总条数
    private Long total;
    //问卷id
    private Long questionId;
}