package com.stxy.smsadmin.exception;

import com.stxy.smsadmin.enums.BaseEnum;
import lombok.Getter;

/**
 * Created by Ldlood on 2017/7/22.
 */
@Getter
public class DemoException extends RuntimeException {
    private String code;
    private String message;

    public DemoException(BaseEnum baseEnum) {
        super(baseEnum.getMessage());
        this.code = baseEnum.getCode();
    }

    public DemoException(String code, String message) {
        super(message);
        this.code = code;
    }


}
