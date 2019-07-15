package com.tuandai.ms.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author DONGWEI8
 * @date 2019年7月4日11:31:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckRepeatSubmit {
    /**
     * redis 锁key的前缀
     *
     * @return redis 锁key的前缀
     */
    String prefix() default "repeat";

    /**
     * 过期秒数,默认为2
     *
     * @return 轮询锁的时间
     */
    long expire() default 2;

    /**
     * 超时时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * <p>Key的分隔符（默认 :）</p>
     * <p>生成的Key：repeatSubmit:saveTransfer:685bb3c4dba1258273269be64f062223</p>
     *
     * @return String
     */
    String delimiter() default ":";

    /**
     * 过滤字段
     *
     * @return
     */
    String[] filter() default {"traceLogId"};
}