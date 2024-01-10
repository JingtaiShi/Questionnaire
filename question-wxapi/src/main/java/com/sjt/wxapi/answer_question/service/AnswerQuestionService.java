package com.sjt.wxapi.answer_question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjt.wxapi.answer_question.entity.AnswerQuestion;
import com.sjt.wxapi.answer_question.entity.CommitParm;

/**
 * @author Rocky
 * @create 2022-02-1810:17
 */
public interface AnswerQuestionService extends IService<AnswerQuestion>  {
    void saveCommit(CommitParm parm);
}
