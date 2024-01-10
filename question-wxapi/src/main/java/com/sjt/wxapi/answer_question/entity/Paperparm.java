package com.sjt.wxapi.answer_question.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-2110:25
 */
@Data
public class Paperparm implements Serializable {
    private Long paperId;
    private String paperType;
    private String paperValue;
    private Long questionId;
}
