package cc.niushuai.project.shuaipush.common.util;

import cn.hutool.extra.spring.SpringUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 校验工具类
 *
 * @author niushuai
 * @date 2022/8/5 14:22
 */
public class ValidatorUtil {

    public static <T> String validate(T obj) {
        return validate(obj, Default.class);
    }

    public static <T> String validate(T obj, Class<?> clazz) {
        Set<ConstraintViolation<T>> validate = SpringUtil.getBean(Validator.class).validate(obj, clazz);
        if (!validate.isEmpty()) {
            return validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        }
        return null;
    }
}
