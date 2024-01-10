package com.sjt.web.sys_paper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjt.web.question.entity.SysPaperParm;
import com.sjt.web.sys_paper.entity.SysPaper;
import com.sjt.web.sys_paper.mapper.SysPaperMapper;
import com.sjt.web.sys_paper.service.SysPaperService;
import com.sjt.web.sys_paper_choice.entity.SysPaperChoice;
import com.sjt.web.sys_paper_choice.mapper.SysPaperChoiceMapper;
import com.sjt.web.sys_paper_choice.service.SysPaperChoiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-0916:32
 */
@Service
public class SysPaperServiceImpl extends ServiceImpl<SysPaperMapper, SysPaper> implements SysPaperService {
    @Resource
    private SysPaperMapper sysPaperMapper;
    @Autowired
    private SysPaperChoiceService sysPaperChoiceService;
    @Resource
    private SysPaperChoiceMapper sysPaperChoiceMapper;

    @Override
    @Transactional
    public void savePaper(List<SysPaper> paperList) {
        //先删除，再保存
        for (int i=0;i<paperList.size();i++){
            //判断是否是单选、多选，需要删除对应的选项数据
            if (paperList.get(i).getPaperType().equals("1") || paperList.get(i).getPaperType().equals("2")){
                //删除选项，构造删除的条件
                QueryWrapper<SysPaperChoice> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(SysPaperChoice::getPaperId, paperList.get(i).getPaperId());
                //删除
                sysPaperChoiceService.remove(queryWrapper);
            }
            //删除试题
//            this.baseMapper.deleteById(paperList.get(i).getPaperId());
            QueryWrapper<SysPaper> query = new QueryWrapper<>();
            query.lambda().eq(SysPaper::getQuestionId, paperList.get(i).getQuestionId());
            this.baseMapper.delete(query);
//            sysPaperMapper.deleteById(paperList.get(i).getPaperId());
        }
        //保存
        for (int k=0;k<paperList.size();k++){
            //先保存试题
            SysPaper sysPaper = new SysPaper();
            BeanUtils.copyProperties(paperList.get(k),sysPaper);
            //试题序号
            sysPaper.setPaperOrder(k+1L);
            //保存试题
            this.baseMapper.insert(sysPaper);
            //判断是否是单选，多选；需要保存选项
            if (paperList.get(k).getPaperType().equals("1") || paperList.get(k).getPaperType().equals("2")){
                //批量保存，保存前需要设置试题的id
                paperList.get(k).getPaperChoice().forEach(item ->{
                    //设置试题的id
                    item.setPaperId(sysPaper.getPaperId());
                });
                //保存选项
                sysPaperChoiceService.saveBatch(paperList.get(k).getPaperChoice());
            }
        }
    }

    @Override
    public List<SysPaper> getList(String questionId) {
        // 构造查询条件
        QueryWrapper<SysPaper> query = new QueryWrapper<>();
        query.lambda().eq(SysPaper::getQuestionId,questionId);
        //试题列表
        List<SysPaper> paperList = this.baseMapper.selectList(query);
        //如果是单选，多选，需要查询对应的选项
        if(paperList.size() > 0){
            for (int i=0;i<paperList.size();i++){
                if(paperList.get(i).getPaperType().equals("1") || paperList.get(i).getPaperType().equals("2")){
                    //构造查询选项的条件
                    QueryWrapper<SysPaperChoice> queryWrapper = new QueryWrapper<>();
                    queryWrapper.lambda().eq(SysPaperChoice::getPaperId,paperList.get(i).getPaperId());
                    //查询选项
                    List<SysPaperChoice> list = sysPaperChoiceService.list(queryWrapper);
                    //把查询到的选项设置到试题
                    paperList.get(i).setPaperChoice(list);
                }
            }
        }

        return paperList;
    }

    @Override
    public List<SysPaperParm> getPaperList(Long questionId) {
        //根据问卷id查询所有的试题列表
        List<SysPaper> paperList = sysPaperMapper.listPaper(questionId);
        List<SysPaperParm> list = new ArrayList<>();
        if(paperList != null && paperList.size() > 0){
            //循环试题列表
            for(int i = 0;i<paperList.size();i++){
                SysPaperParm parm = new SysPaperParm();
                BeanUtils.copyProperties(paperList.get(i),parm);
                //判断是否是单选或者多选，查询对于的选项
                List<SysPaperChoice> choiceList = sysPaperChoiceMapper.getChoiceList(paperList.get(i).getPaperId());
                //设置试题对应的选项
                parm.setPaperChoice(choiceList);
                list.add(parm);
            }
        }
        return list;
    }
}
