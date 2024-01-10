package com.sjt.web.sys_paper.controller;

import com.sjt.utils.ResultUtils;
import com.sjt.utils.ResultVo;
import com.sjt.web.sys_paper.entity.SysPaper;
import com.sjt.web.sys_paper.service.SysPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试题模块控制器
 * @author Rocky
 * @create 2022-02-0916:34
 */
@RestController
@RequestMapping("/api/sysPaper")
public class SysPaperController {
    @Autowired
    private SysPaperService sysPaperService;

    //保存试题
    @PostMapping("/savePaper")
    public ResultVo savePaper(@RequestBody List<SysPaper> paperList){
        sysPaperService.savePaper(paperList);
        return ResultUtils.success("设计试题成功!");
    }

    //编辑回显
    @GetMapping("/getPaperList")
    public ResultVo getPaperList(String questionId){
        List<SysPaper> list = sysPaperService.getList(questionId);
        return ResultUtils.success("查询成功",list);
    }
}
