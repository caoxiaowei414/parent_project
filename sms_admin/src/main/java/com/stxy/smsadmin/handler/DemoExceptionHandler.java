package com.stxy.smsadmin.handler;


import com.stxy.smsadmin.VO.ResultVO;
import com.stxy.smsadmin.exception.DemoException;
import com.stxy.smsadmin.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Ldlood on 2017/8/13.
 */
@ControllerAdvice
public class DemoExceptionHandler {



    @ExceptionHandler(value = DemoException.class)
    @ResponseBody
    public ResultVO handlerSellerException(DemoException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());

    }



}
