package com.tuandai.ms.common.generator;

import org.aspectj.lang.ProceedingJoinPoint;

public interface RepeatSubmitKeyGenerator {
 
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
