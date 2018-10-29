package com.stxy.smsadmin.dataobject.mapper;

import com.stxy.smsadmin.VO.DemoVO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据库层  接口操作类
 */
public interface DemoMapper {


    @Select("select * from test ")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "desc", property = "desc")
    })
    List< DemoVO >getAll();

}
