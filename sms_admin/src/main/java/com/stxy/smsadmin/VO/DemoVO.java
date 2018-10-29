package com.stxy.smsadmin.VO;

import lombok.Data;
import java.io.Serializable;


/**  VO[value object]值对象  页面对象 返回前端显示  */
@Data
public class DemoVO  implements Serializable {

    String id ;
    String name ;
    String desc ;
}
