package com.sjt.web.sys_paper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sjt.web.sys_paper_choice.entity.SysPaperChoice;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Rocky
 * @create 2022-02-0916:00
 */
@Data
@TableName("sys_paper")
public class SysPaper implements Serializable {
    //试题id
    @TableId(type = IdType.AUTO)
    private Long paperId;
    //问卷id
    private Long questionId;
    //试题标题
    private String paperTitle;
    //试题类型
    private String paperType;
    //试题序号
    private Long paperOrder;

    //接收前端传来的参数，单选，多选题对应的选项,不属于试题表，需要排除
    @TableField(exist = false)
    private List<SysPaperChoice> paperChoice;
}
