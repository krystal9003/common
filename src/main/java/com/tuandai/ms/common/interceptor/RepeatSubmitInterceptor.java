//package com.tuandai.ms.common.interceptor;
//
//import com.tuandai.ms.common.annotation.CheckRepeatSubmit;
//import com.tuandai.ms.common.generator.RepeatSubmitKeyGenerator;
//import groovy.util.logging.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
//@Component
//@Aspect
//@Slf4j
//public class RepeatSubmitInterceptor {
//
//    @Autowired
//    private RepeatSubmitKeyGenerator cacheKeyGenerator;
//
//    @Pointcut("execution(* com.tuandai.*.*(..))")
//    public void pointCut() {
//    }
//
//    /**
//     * 捕获所有controller方法调用，遇到有BaseOperatorRequest子类传参的，获取当前请求的用户，自动设置BaseOperatorRequest中的操作人信息（遇到TradeCommonRequest还要设置operateUser）
//     * @param pjp
//     * @return
//     * @throws Throwable
//     */
//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        Method method = signature.getMethod();
//        CheckRepeatSubmit lock = method.getAnnotation(CheckRepeatSubmit.class);
//        if(lock == null){
//            return pjp.proceed();
//        }
//        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
////        try {
////            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是false
////            if(!JedisUtil.getJedisInstance().execSetnxExpireToCache(lockKey, "TRUE", lock.expire())){
////                throw AdminException.REPEAT_SUBMIT_EXCEPTION;
////            }
////        } finally {
////            //死锁
////            if(JedisUtil.getJedisInstance().execTtlToCache(lockKey).intValue() == -1){
////                JedisUtil.getJedisInstance().execExpireToCache(lockKey, (int)lock.expire());
////            }
////        }
//        return pjp.proceed();
//    }
//}