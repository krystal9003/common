package com.tuandai.ms.common.annotation;

import java.lang.annotation.*;

/**
 * @author DONGWEI8
 * @date 2019年7月4日13:41:24
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CheckRepeatParam {
 
   /**
    * 字段名称
    * @return String
    */
   String name() default "";
}