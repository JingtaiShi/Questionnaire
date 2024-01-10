package com.sjt.wxapi.answer_question.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rocky
 * @create 2022-02-189:44
 */
@Data
@TableName("answer_question")
public class AnswerQuestion implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long answerId;
    private Long questionId;
    private String openid;
    private Date createTime;

}
