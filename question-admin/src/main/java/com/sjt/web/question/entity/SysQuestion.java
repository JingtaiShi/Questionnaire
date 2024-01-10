package com.sjt.web.question.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sjt.web.sys_paper.entity.SysPaper;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-0410:20
 */
@Data
@TableName("sys_question")//指明该实体对应的数据库表名是sys_question
public class SysQuestion implements Serializable {
    //主键 自动递增
    @TableId(type = IdType.AUTO)
    private Long questionId;
    //问卷标题
    private String questionTitle;
    //问卷描述
    private String questionDesc;
    //图片路径
    private String questionImg;
    //问卷状态 0:关闭 1:调查中
    private String questionStatus;
    //序号
    private Long questionOrder;
    //问卷对应的试题列表，不属于问卷表，需要排除
    @TableField(exist = false)
    private List<SysPaper> listPaper;


}
