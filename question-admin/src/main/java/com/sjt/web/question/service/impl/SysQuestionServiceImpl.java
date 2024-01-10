package com.sjt.web.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjt.web.question.entity.SysQuestion;
import com.sjt.web.question.mapper.SysQuestionMapper;
import com.sjt.web.question.service.SysQuestionService;
import org.springframework.stereotype.Service;

/**
 * @author Rocky
 * @create 2022-02-0410:33
 */
@Service
public class SysQuestionServiceImpl extends ServiceImpl<SysQuestionMapper, SysQuestion> implements SysQuestionService {
}
