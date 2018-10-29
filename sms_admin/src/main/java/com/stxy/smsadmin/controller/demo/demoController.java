package com.stxy.smsadmin.controller.demo;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.stxy.smsadmin.VO.DemoVO;
import com.stxy.smsadmin.enums.GlobalEnum;
import com.stxy.smsadmin.form.DemoForm;
import com.stxy.smsadmin.service.demoservice.DemoService;
import com.stxy.smsadmin.utils.RedisOperator;
import com.stxy.smsadmin.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HP
 * 返回 json 格式  == @controller +@ResponseBody
 */
@RestController
@Slf4j
public class demoController {

    /*  @PostMapping    @GetMapping   @DeleteMapping @PutMapping @PatchMapping  @RequestMapping*/


    @Autowired
   private  DemoService demoService ;

    @Autowired
    private RedisOperator redisOperator ;





    /**
     * @param id
     * @return
     */
    @GetMapping(value = "/demo/{id}"  )
    public Object getHttpController(
            @PathVariable("id") @NotNull @Max(9999) String id) {
        System.out.println(id);
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return map;
    }




    @PostMapping(value ="/post" )
    public Object postHttpController(@Valid  DemoForm demoForm

    ){



       /**BindingResult errors  入参 , 处理 参数不合法异常   方法一 :  另有方法二  全局处理
       if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(
                    error -> {
                        FieldError fieldError = (FieldError)error;
                        log.info("错误字段：{} -> 错误信息：{}",
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        );
                    }
            );
        }*/


        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };




        DemoVO demoVO = new DemoVO() ;
        demoVO.setName( demoForm.getName());
        demoVO.setDesc(String.format("手机号码是 : %s" ,demoForm.getPhone()));
        demoVO.setId("78978979");
        return ResultVOUtil.success(GlobalEnum.SUCCESS,demoVO);


    }

    @GetMapping("/getAll")
    public Object getAll(){
        /*
         * 第一个参数是第几页；第二个参数是每页显示条数。
         */
        PageHelper.startPage(1,10);
        System.out.println("sss");
        Object object = demoService.demoGet();
        redisOperator.set("mykey",JSON.toJSON(object).toString());
        return ResultVOUtil.success(GlobalEnum.SUCCESS,object);
    }


}
