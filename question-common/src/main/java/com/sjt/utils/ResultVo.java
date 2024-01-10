package com.sjt.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用返回值的封装
 * @author Rocky
 * @create 2022-01-1311:08
 */
@Data
@AllArgsConstructor
public class ResultVo<T> {
    private String msg;
    private int code;
    private T data;
}
