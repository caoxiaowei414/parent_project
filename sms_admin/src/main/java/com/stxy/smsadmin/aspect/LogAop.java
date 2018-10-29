package com.stxy.smsadmin.aspect;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 打印 方法执行日志
 */
@Component  //声明组件
@Aspect //  声明切面
@ComponentScan  //组件自动扫描
@EnableAspectJAutoProxy //spring自动切换JDK动态代理和CGLIB
@Slf4j
public class LogAop {


    /**
     * 打印类method的名称以及参数
     * @param point 切面
     */
    public void printMethodParams(JoinPoint point){
        if(point == null){
            return;
        }
        /**Signature 包含了方法名、申明类型以及地址等信息*/
        String class_name = point.getTarget().getClass().getName();
        String method_name = point.getSignature().getName();
        //重新定义日志
        log.info("执行的方法 : {}.{} ",class_name ,method_name);
        /**  获取方法的参数值数组。 */
        Object[] method_args = point.getArgs();

        try {
            /**  获取方法参数名称 */
            String[] paramNames = getFieldsName(class_name, method_name);
            /**  打印方法的参数名和参数值 */
            logParam(paramNames,method_args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用javassist来获取方法参数名称
     * @param className    类名
     * @param methodName   方法名
     * @return
     * @throws Exception
     */
    private String[] getFieldsName(String className, String methodName) throws Exception {
        Class<?> clazz = Class.forName(className);
        String clazzName = clazz.getName();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(clazz);
        pool.insertClassPath(classPath);

        CtClass ctClass = pool.get(clazzName);
        CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if(attr == null){
            return null;
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i=0;i<paramsArgsName.length;i++){
            paramsArgsName[i] = attr.variableName(i + pos);
        }
        return paramsArgsName;
    }


    /**
     * 判断是否为基本类型：包括String
     * @param clazz clazz
     * @return  true：是;     false：不是
     */
    private boolean isPrimite(Class<?> clazz){
        return clazz.isPrimitive() || clazz == String.class;
    }


    /**
     * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
     * @param paramsArgsName    方法参数名数组
     * @param paramsArgsValue   方法参数值数组
     */
    private void logParam(String[] paramsArgsName,Object[] paramsArgsValue){
        if(ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)){
            log.info("该方法没有参数");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<paramsArgsName.length;i++){
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];
            buffer.append(name +" = ");
            if(isPrimite(value.getClass())){
                buffer.append(value + "  ,");
            }else {
                buffer.append(value.toString() + "  ,");
            }
        }
        log.info("参数是 : {}" ,buffer.toString());
    }

    /**
     * Before       : 在方法执行前进行切面
     * execution    : 定义切面表达式
     *    第一个* :方法返回值类型，*代表所有类型
     *    第二个* :包路径的通配符
     *    第三个..* :表示impl这个目录下所有的类，包括子目录的类
     *    第四个*(..) : *表示所有任意方法名,..表示任意参数
     */

    /**控制层切面*/
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void controllerLogPointCut() {  }

    /**逻辑层切面*/
    @Pointcut("execution(public * com.stxy.smsadmin.service.*.impl..*.*(..))")
    public void serviceLogPointCut() {}



    /**打印服务层执行日志*/
    @Before("serviceLogPointCut()")
    public void serviceBefore(JoinPoint point) { this.printMethodParams(point);  }


    /**打印控制层执行日志*/
    @Before("controllerLogPointCut()")
    public void  controllerBefore(JoinPoint point) { this.printMethodParams(point); }


}