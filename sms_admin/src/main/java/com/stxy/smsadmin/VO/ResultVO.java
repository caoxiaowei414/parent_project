package com.stxy.smsadmin.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回数据
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 3236329195874147801L;
    /** 错误码. */
    private String code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
