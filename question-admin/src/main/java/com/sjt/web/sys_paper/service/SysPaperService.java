package com.sjt.web.sys_paper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjt.web.question.entity.SysPaperParm;
import com.sjt.web.sys_paper.entity.SysPaper;

import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-0916:31
 */
public interface SysPaperService extends IService<SysPaper> {
    //保存试题
    void savePaper(List<SysPaper> paperList);
    //试题回写
    List<SysPaper> getList(String questionId);
    //根据问卷id查询试题列表回显
    List<SysPaperParm> getPaperList(Long questionId);

}
