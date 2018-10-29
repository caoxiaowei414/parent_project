package com.stxy.smsadmin.utils;


import com.stxy.smsadmin.VO.ResultVO;
import com.stxy.smsadmin.enums.BaseEnum;


public class ResultVOUtil {



    /*成功返回*/
    public static ResultVO success() {
        return success(null);
    }
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode("0");
        resultVO.setMsg("成功");
        return resultVO;
    }

    /*成功返回携带返回数据*/
    public static ResultVO success(BaseEnum baseEnum, Object object ) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(baseEnum.getCode());
        resultVO.setMsg(baseEnum.getMessage());
        return resultVO;
    }
    /*错误返回*/
    public static ResultVO error(String code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }



}
