import com.tuandai.ms.common.annotation.CheckRepeatParam;
import com.tuandai.ms.common.annotation.CheckRepeatSubmit;
import com.tuandai.ms.common.generator.RepeatSubmitKeyGenerator;
import com.tuandai.ms.util.MD5Util;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class LockKeyGenerator implements RepeatSubmitKeyGenerator {

    @Override
    public String getLockKey(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CheckRepeatSubmit lockAnnotation = method.getAnnotation(CheckRepeatSubmit.class);
        String[] filterFields = lockAnnotation.filter();
        Set<String> filterFieldsSet = new HashSet<>(Arrays.asList(filterFields));
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        // 默认解析方法里面带 CheckRepeatParam 注解的属性,如果没有尝试着解析实体对象中的
        for (int i = 0; i < parameters.length; i++) {
            final CheckRepeatParam annotation = parameters[i].getAnnotation(CheckRepeatParam.class);
            if (annotation == null) {
                continue;
            }
            final Object object = args[i];
            Object objectTemp = object;
            final Field[] fields = objectTemp.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (!CollectionUtils.isEmpty(filterFieldsSet) && filterFieldsSet.contains(field.getName())) {
                    try {
                        //前端每次请求到后端traceLogId都会变化，在重复提交的场景下，其他字段都一样，所以只需要排除traceLogId字段
                        field.set(objectTemp, null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                builder.append(lockAnnotation.delimiter()).append(ReflectionUtils.getField(field, objectTemp));
            }
        }
        return "repeatSubmit:" + method.getName() + lockAnnotation.delimiter() + MD5Util.MD5Encode(builder.toString());
    }
}