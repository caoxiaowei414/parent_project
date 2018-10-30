package com.stxy.smsadmin.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 *前端表单对象
 */
@Data
public class DemoForm {
    /**
     * 姓名
     */
    @NotBlank(message = "姓名必填")
    private String name;


    /**
     * 买家手机号
     */
    @NotBlank(message = "手机号必填")
    private String phone;


}
