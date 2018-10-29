package com.stxy.smsadmin.handler;

import com.stxy.smsadmin.enums.GlobalEnum;
import com.stxy.smsadmin.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
    /*入参校验异常*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("必填校验异常: {}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return ResultVOUtil.error(GlobalEnum.PARAMETER_EXCEPTION.getCode() , fieldError.getDefaultMessage());
    }


    /*入参校验异常*/
    @ExceptionHandler({BindException.class})
    public Object handleBindException(BindException ex ) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return ResultVOUtil.error(GlobalEnum.PARAMETER_EXCEPTION.getCode() , fieldError.getDefaultMessage());
    }

    /*全局异常*/
    @ExceptionHandler({Exception.class})
    public Object globalException(Exception e ) {
        log.info("出现未知异常 {}",e.getMessage());
        e.printStackTrace();
        return ResultVOUtil.error(GlobalEnum.UNKNOW_EXECPTION.getCode() ,e.getMessage());
    }



}