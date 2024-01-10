package com.sjt.wxapi.answer_paper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-2110:30
 */
@Data
@TableName("answer_paper")
public class AnswerPaper implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long answerPaperId;
    private Long paperId;
    private String openid;
    private String paperType;
    private String paperValue;

}
