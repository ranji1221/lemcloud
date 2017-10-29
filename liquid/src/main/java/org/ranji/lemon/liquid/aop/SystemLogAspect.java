package org.ranji.lemon.liquid.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.ranji.lemon.core.annotation.SystemControllerLog;
import org.ranji.lemon.core.annotation.SystemControllerPermission;
import org.ranji.lemon.core.system.SystemContext;
import org.ranji.lemon.liquid.model.log.SystemLog;
import org.ranji.lemon.liquid.service.log.prototype.ISystemLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the"License"); 
 * you may not use this file except in compliance with the License.  
 * You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
 * See the License for the specific language governing permissions and limitations under the License.
 * Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * 系统日志切面
 * @author RanJi
 * @date 2017-9-14
 * @since JDK1.7
 * @version 1.0
 */


@Component
@Aspect
public class SystemLogAspect {
	private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);

    private static final ThreadLocal<Long> beginTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal BeginTime");
    private static final ThreadLocal<SystemLog> logThreadLocal =  new NamedThreadLocal<SystemLog>("ThreadLocal Log");
    
    
    @Autowired(required=false)
    private HttpServletRequest request;

   // @Autowired
   // private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    @Autowired
    private ISystemLogService logService;
    
    /**
     * SpringMVC中的Controller层的切点，注解拦截
     */
    @Pointcut("@annotation(org.ranji.lemon.core.annotation.SystemControllerLog)")
    public void controllerAspect(){}
    
    /**
     * 前置通知 用于拦截Controller层记录用户的操作的开始时间
     * @param joinPoint 切点
     * @throws InterruptedException 
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException{
        beginTimeThreadLocal.set(System.currentTimeMillis());//线程绑定变量（该数据只有当前请求的线程可见）  
        if (logger.isDebugEnabled()){//这里日志级别为debug
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(beginTimeThreadLocal.get()), request.getRequestURI());
        }
    }
    
    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    @SuppressWarnings("unchecked")
	@After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        //System.out.println("doAfter"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(System.currentTimeMillis()));
        String title="";
        String type="info";                       //日志类型(info:入库,error:错误)
      
        String remoteAddr=request.getRemoteAddr();//请求的IP
        String requestUri=request.getRequestURI();//请求的Uri
        String method=request.getMethod();        //请求的方法类型(post/get)
        Map<String,String[]> params=request.getParameterMap(); //请求提交的参数

        try {
            title=getControllerMethodDescriptionInfo(joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }    
        
        // 打印JVM信息。
        long beginTime = beginTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
        long endTime = System.currentTimeMillis();  //2、结束时间  
        if (logger.isDebugEnabled()){
            logger.debug("计时结束：{}  URI: {}  耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime), 
                    request.getRequestURI(), 
                    new SimpleDateFormat("mm:ss:SSS").format(new Date(endTime-beginTime-28800000)), //--减去8小时的东八区时间
                    Runtime.getRuntime().maxMemory()/1024/1024, 
                    Runtime.getRuntime().totalMemory()/1024/1024, 
                    Runtime.getRuntime().freeMemory()/1024/1024, 
                    (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024); 

        }

        SystemLog log=new SystemLog();
        log.setLogTitle(title);
        log.setLogType(type);
        log.setRemoteAddr(remoteAddr);
        log.setRequestUri(requestUri);
        log.setAuthStatus(SystemContext.getAuthStatus());
        SystemContext.removeAuthStatus();
        log.setMethod(method);
        log.setMapToParams(params);
        log.setUserName(SecurityUtils.getSubject().getPrincipal()!=null ? (String)SecurityUtils.getSubject().getPrincipal(): "visitor" );
        log.setOperateDate(new Date(beginTimeThreadLocal.get()));
        log.setTimeout(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(endTime-beginTime-28800000))); //--减去8小时的东八区时间
//System.out.println(log);   //-- 输出日志信息
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(endTime))); //-- 输出结束时间
        //1.直接执行保存操作
        logService.save(log);
//System.out.println(log.getId());
		
        //2.优化:异步保存日志
        //new SaveLogThread(log, logService).start();

        //3.再优化:通过线程池来执行日志保存  (暂时注释掉)
        //threadPoolTaskExecutor.execute(new SaveLogThread(log, logService));
       // System.out.println("thread1");
       
		logThreadLocal.set(log);	
    }
    /**
     *  异常通知 记录操作报错日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")  
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    	
        SystemLog log = logThreadLocal.get();
        //System.out.println(log.getId());
        log.setLogType("error");
        log.setException(e.toString());
        logService.update(log);
        //threadPoolTaskExecutor.execute(new UpdateLogThread(log, logService));
        //System.out.println("thead2");
    }

    
    
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param joinPoint 切点
     * @return discription
     */
    public static String getControllerMethodDescriptionInfo(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerLog controllerLog = method
                .getAnnotation(SystemControllerLog.class);
        String discription = controllerLog.description();
        return discription;
    }
    
    /**
     * 获取SystemControllerPermission注解中的Value信息 用于Controller层注解
     * 
     * @param ProceedingJoinPoint 切点
     * @return permissionInfo
     */
    public static String getControllerMethodPemissionInfo(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerPermission controllerPermission = method
                .getAnnotation(SystemControllerPermission.class);
        return controllerPermission != null ? controllerPermission.value():"";
    }
    
    
    /**
     * 保存日志线程
     */
   /*
    private static class SaveLogThread implements Runnable {
        private SystemLog log;
        private ISystemLogService logService;

        public SaveLogThread(SystemLog log, ISystemLogService logService) {
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            logService.save(log);
            System.out.println("thead3");
        }
    }*/

    /**
     * 日志更新线程
     */
    /*
    private static class UpdateLogThread implements Runnable {
        private SystemLog log;
        private ISystemLogService logService;

        public UpdateLogThread(SystemLog log, ISystemLogService logService) {
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            this.logService.update(log);
            System.out.println("thread4");
        }
    }*/
}
