package com.sjt.wxapi.answer_question.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-2110:25
 */
@Data
public class CommitParm implements Serializable {
    private String openid;
    private List<Paperparm> paperList = new ArrayList<>();
    private Long questionId;
}
