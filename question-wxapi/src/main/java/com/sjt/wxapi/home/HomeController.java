package com.sjt.wxapi.home;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjt.wxapi.answer_question.entity.AnswerQuestion;
import com.sjt.wxapi.answer_question.entity.CommitParm;
import com.sjt.wxapi.answer_question.service.AnswerQuestionService;
import com.sjt.wxapi.question.entity.SysQuestion;
import com.sjt.wxapi.question.entity.SysQuestionParm;
import com.sjt.wxapi.question.service.SysQuestionService;
import com.sjt.utils.ResultUtils;
import com.sjt.utils.ResultVo;
import com.sjt.wxapi.sys_paper.entity.SysPaper;
import com.sjt.wxapi.sys_paper.service.SysPaperService;
import com.sjt.wxapi.sys_paper_choice.entity.SysPaperChoice;
import com.sjt.wxapi.sys_paper_choice.service.SysPaperChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序首页控制器
 * @author Rocky
 * @create 2022-02-1510:55
 */
@RestController
@RequestMapping("/wxapi/home")
public class HomeController {
    @Autowired
    private SysQuestionService sysQuestionService;
    @Autowired
    private AnswerQuestionService answerQuestionService;
    @Autowired
    private SysPaperService sysPaperService;
    @Autowired
    private SysPaperChoiceService sysPaperChoiceService;


    /**
     * 查询首页列表
     */
    @GetMapping("/getList")
    public ResultVo getList(SysQuestionParm sysQuestionParm) {
        //构造分页对象
        IPage<SysQuestion> page = new Page<>();
        page.setCurrent(sysQuestionParm.getCurrentPage());
        page.setSize(sysQuestionParm.getPageSize());
        IPage<SysQuestion> list = sysQuestionService.getList(page);
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 问卷详情查询
     * 1.根据问卷id查询
     * 2.根据问卷id和openid查询
     */
    @GetMapping("/getDetails")
    public ResultVo getDetails(String questionId, String openid) {
        //1.根据问卷id查询
        SysQuestion question = sysQuestionService.getById(questionId);
        //2.根据问卷id和openid查询
        //构造查询条件
        QueryWrapper<AnswerQuestion> query = new QueryWrapper<>();
        query.lambda().eq(AnswerQuestion::getQuestionId, questionId)
                .eq(AnswerQuestion::getOpenid, openid);
        AnswerQuestion answerQuestion = answerQuestionService.getOne(query);
        if (answerQuestion != null) {
            question.setStatus("1"); //已答卷
        } else {
            question.setStatus("0"); //未答卷
        }
        return ResultUtils.success("查询成功", question);
    }

    /**
     * 根据问卷id查询试题列表
     * 1.查询问卷
     * 2.根据问卷的id查试题列表
     * 3.查询单选和多选对应的选项
     */
    @GetMapping("/getPaperList")
    public ResultVo getPaperList(String questionId) {
        //1.查询问卷
        SysQuestion question = sysQuestionService.getById(questionId);
        //2.根据问卷的id查试题列表
        //构造查询条件
        QueryWrapper<SysPaper> query = new QueryWrapper<SysPaper>();
        query.lambda().eq(SysPaper::getQuestionId, questionId);
        //试题列表
        List<SysPaper> paperList = sysPaperService.list(query);
        //3.查询单选和多选对应的选项
        for (int i = 0; i < paperList.size(); i++) {
            //如果是单选或多选的时候需要查询选项
            if (paperList.get(i).getPaperType().equals("1") || paperList.get(i).getPaperType().equals("2")) {
                QueryWrapper<SysPaperChoice> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(SysPaperChoice::getPaperId, paperList.get(i).getPaperId());
                List<SysPaperChoice> paperChoices = sysPaperChoiceService.list(queryWrapper);
                //设置单选和多选的选项
                paperList.get(i).setPaperChoice(paperChoices);
            }
        }
        //给问卷设置试题
        question.setListPaper(paperList);
        return ResultUtils.success("查询成功", question);
    }

    /**
     * 保存问卷
     */
    @PostMapping("/saveCommit")
    public ResultVo saveCommit(@RequestBody CommitParm parm) {
        answerQuestionService.saveCommit(parm);
        return ResultUtils.success("提交成功!");
    }

    /**
     * 查询我的列表
     */
    @GetMapping("/getMyQuestionList")
    public ResultVo getMyQuestionList(SysQuestionParm sysQuestionParm) {
        //构造分页对象
        IPage<SysQuestion> page = new Page<>();
        page.setCurrent(sysQuestionParm.getCurrentPage());
        page.setSize(sysQuestionParm.getPageSize());
        IPage<SysQuestion> list = sysQuestionService.getMyQuestionList(page, sysQuestionParm.getOpenid());
        return ResultUtils.success("查询成功", list);
    }

    /**
     * 查询试题回写
     */
    @GetMapping("/getMyPaperListShow")
    public ResultVo getMyPaperListShow(String openid, Long questionId) {
        SysQuestion list = sysQuestionService.getMyPaperList(questionId, openid);
        return ResultUtils.success("查询成功", list);
    }
}
