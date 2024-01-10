package com.sjt.wxapi.answer_paper_choice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Rocky
 * @create 2022-02-2110:38
 */
@Data
@TableName("answer_paper_choice")
public class AnswerPaperChoice implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long answerChoiceId;
    private Long choiceId;
    private Long paperId;
    private String openid;

}
