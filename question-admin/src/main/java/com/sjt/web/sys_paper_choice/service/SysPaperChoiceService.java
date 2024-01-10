package com.sjt.web.sys_paper_choice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjt.web.question.entity.ResultPaperChoice;
import com.sjt.web.sys_paper_choice.entity.SysPaperChoice;

import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-0916:24
 */
public interface SysPaperChoiceService extends IService<SysPaperChoice> {
    List<SysPaperChoice> getListPaperChoice(Long questionId);
    //查询选项的票数统计
    List<ResultPaperChoice> getListPaperChoices(Long questionId);
}
