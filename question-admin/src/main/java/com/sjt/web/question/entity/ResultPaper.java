package com.sjt.web.question.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultPaper implements Serializable {
    //试题id
    private Long paperId;
    //问卷id
    private Long questionId;
    //标题
    private String paperTitle;
    //试题类型
    private String paperType;
    private Long paperOrder;
    //单选和多选对应的选项
    private List<com.sjt.web.question.entity.ResultPaperChoice> paperChoice;
}