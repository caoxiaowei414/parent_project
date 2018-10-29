package com.stxy.smsadmin.service.demoservice.impl;

import com.stxy.smsadmin.dataobject.mapper.DemoMapper;
import com.stxy.smsadmin.service.demoservice.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private  DemoMapper demoMapper ;

    @Override
    public Object demoGet() {
        return demoMapper.getAll();
    }
}
