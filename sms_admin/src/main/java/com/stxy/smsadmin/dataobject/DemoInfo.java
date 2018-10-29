package com.stxy.smsadmin.dataobject;

import lombok.Data;

/**和数据库 表结构一一对应*/
@Data
//@Entity
public class DemoInfo {

//    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
