package com.sjt.web.sys_paper_choice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjt.web.question.entity.ResultPaperChoice;
import com.sjt.web.sys_paper_choice.entity.SysPaperChoice;
import com.sjt.web.sys_paper_choice.mapper.SysPaperChoiceMapper;
import com.sjt.web.sys_paper_choice.service.SysPaperChoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-0916:26
 */
@Service
public class SysPaperChoiceServiceImpl extends ServiceImpl<SysPaperChoiceMapper, SysPaperChoice> implements SysPaperChoiceService {
    @Override
    public List<SysPaperChoice> getListPaperChoice(Long questionId) {
        return this.baseMapper.getListPaperChoice(questionId);
    }

    @Override
    public List<ResultPaperChoice> getListPaperChoices(Long questionId) {
        return this.baseMapper.getListPaperChoices(questionId);
    }
}
