package com.sjt.web.question.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjt.web.question.entity.*;
import com.sjt.web.sys_paper.entity.SysPaper;
import com.sjt.web.sys_paper.service.SysPaperService;
import com.sjt.web.sys_paper_choice.entity.SysPaperChoice;
import com.sjt.web.sys_paper_choice.service.SysPaperChoiceService;
import org.apache.commons.lang.StringUtils;
import com.sjt.utils.ResultUtils;
import com.sjt.utils.ResultVo;
import com.sjt.web.question.service.SysQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 问卷控制器
 * @author Rocky
 * @create 2022-02-0410:43
 */
@RestController
@RequestMapping("/api/question")
public class SysQuestionController {
    @Autowired
    private SysQuestionService sysQuestionService;

    @Autowired
    private SysPaperService sysPaperService;

    @Autowired
    private SysPaperChoiceService sysPaperChoiceService;

    /**
     * 新增问卷
     */
    @PostMapping
    public ResultVo add(@RequestBody SysQuestion sysQuestion){
        boolean save = sysQuestionService.save(sysQuestion);
        if(save){
            return ResultUtils.success("新增问卷成功!");
        }
        return ResultUtils.error("新增问卷失败!");
    }

    /**
     * 编辑问卷
     */
    @PutMapping
    public ResultVo edit(@RequestBody SysQuestion sysQuestion){
        boolean b = sysQuestionService.updateById(sysQuestion);
        if(b){
            return ResultUtils.success("编辑问卷成功!");
        }
        return ResultUtils.error("编辑问卷失败!");
    }

    /**
     * 删除问卷
     */
    @DeleteMapping("/{questionId}")
    public ResultVo delete(@PathVariable("questionId") Long questionId){
        boolean b = sysQuestionService.removeById(questionId);
        if(b){
            return ResultUtils.success("删除问卷成功!");
        }
        return ResultUtils.error("删除问卷失败!");
    }

    /**
     * 问卷列表
     */
    @GetMapping("/list")
    public ResultVo getList(SysQuestionParm sysQuestionParm){
        //构造分页对象
        IPage<SysQuestion> page = new Page<>();
        page.setSize(sysQuestionParm.getPageSize());
        page.setCurrent(sysQuestionParm.getCurrentPage());
        //构造查询条件
        QueryWrapper<SysQuestion> query = new QueryWrapper<>();
        //模糊查询
        if(StringUtils.isNotEmpty(sysQuestionParm.getQuestionTitle())){
            query.lambda().like(SysQuestion::getQuestionTitle, sysQuestionParm.getQuestionTitle());
        }
        //排序
        query.lambda().orderByAsc(SysQuestion::getQuestionOrder);
        IPage<SysQuestion> list = sysQuestionService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 问卷统计查询
     */
    @GetMapping("/getTotalList")
    public ResultVo getTotalList(Long questionId){
        //1.查询问卷详情
        SysQuestion sysQuestion = sysQuestionService.getById(questionId);
        //2.查询问卷所有试题
        QueryWrapper<SysPaper> query = new QueryWrapper<>();
        query.lambda().eq(SysPaper::getQuestionId,questionId)
                .orderByAsc(SysPaper::getPaperOrder);
        List<SysPaper> PaperList = sysPaperService.list(query);
        //3.查询试题对应的选项统计
        List<SysPaperChoice> choiceList = sysPaperChoiceService.getListPaperChoice(questionId);
        for (int i = 0; i < PaperList.size(); i++) {
            List<SysPaperChoice> paperChoice = new ArrayList<>();
            Long paperId = PaperList.get(i).getPaperId();
            choiceList.stream().filter(item -> item.getPaperId().equals(paperId)).forEach(item -> {
                SysPaperChoice choice = new SysPaperChoice();
                BeanUtils.copyProperties(item, choice);
                paperChoice.add(choice);
            });
            //设置单选、多选的选项
            PaperList.get(i).setPaperChoice(paperChoice);
        }
        sysQuestion.setListPaper(PaperList);
        return ResultUtils.success("查询成功", sysQuestion);
    }

    @GetMapping("/getTotalListEchart")
    @ResponseBody
    public ResultVo getTotalListEchart(Long questionId) {
        //根据问卷id查询所有的试题
        List<SysPaperParm> paperList = sysPaperService.getPaperList(questionId);
        //存放返回值
        List<ResultPaper> resultList = new ArrayList<>();
        //查询试题对应的选项
        List<ResultPaperChoice> choiceList = sysPaperChoiceService.getListPaperChoices(questionId);
        if (paperList.size() > 0) {
            for (int i = 0; i < paperList.size(); i++) {
                //定义返回值
                ResultPaper parm = new ResultPaper();
                //定义存放试题选项
                List<ResultPaperChoice> paperChoices = new ArrayList<>();
                //获取试题的id
                Long paperId = paperList.get(i).getPaperId();
                choiceList.stream().filter(item -> item.getPaperId().equals(paperId)).forEach(item -> {
                    ResultPaperChoice choice = new ResultPaperChoice();
                    BeanUtils.copyProperties(item, choice);
                    //把选项添加到试题
                    paperChoices.add(choice);
                });
                BeanUtils.copyProperties(paperList.get(i), parm);
                parm.setPaperChoice(paperChoices);
                resultList.add(parm);
            }
        }
        return ResultUtils.success("查询成功", resultList);
    }
}
