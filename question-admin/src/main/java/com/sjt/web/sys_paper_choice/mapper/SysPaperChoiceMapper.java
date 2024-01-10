package com.sjt.web.sys_paper_choice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjt.web.question.entity.ResultPaperChoice;
import com.sjt.web.sys_paper_choice.entity.SysPaperChoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-0916:19
 */
public interface SysPaperChoiceMapper extends BaseMapper<SysPaperChoice> {
    List<SysPaperChoice> getListPaperChoice(@Param("questionId") Long questionId);
    //根据试题id查询选项列表
    List<SysPaperChoice> getChoiceList(@Param("paperId") Long paperId);
    //查询选项的票数统计
    List<ResultPaperChoice> getListPaperChoices(@Param("questionId") Long questionId);
}
