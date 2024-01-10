package com.sjt.web.sys_paper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjt.web.sys_paper.entity.SysPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-0916:29
 */
public interface SysPaperMapper extends BaseMapper<SysPaper> {
    //根据问卷id查询试题列表
    List<SysPaper> listPaper(@Param("questionId") Long questionId);
}
