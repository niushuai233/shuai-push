package cc.niushuai.project.shuaipush.common.base;

import java.lang.reflect.InvocationTargetException;

/**
 * 基础枚举接口 提供match方法
 *
 * @author niushuai
 * @date 2022/8/5 9:50
 */
public interface BaseEnum<K, V extends Enum<?>> {

    /**
     * 是否包含指定的value
     *
     * @param value key
     * @author niushuai
     * @date: 2022/8/5 9:54
     * @return: {@link Boolean} 匹配结果
     */
    static <K, V extends Enum<?>> boolean containsValue(K value, Class<V> clazz) {
        return null != matchEnum(value, clazz);
    }

    /**
     * 根据值匹配相应的枚举对象
     *
     * @param value key
     * @author niushuai
     * @date: 2022/8/5 9:54
     * @return: {@link V} 匹配结果
     */
    static <K, V extends Enum<?>> V matchEnum(K value, Class<V> clazz) {

        try {
            V[] enumConstants = clazz.getEnumConstants();
            for (V v : enumConstants) {
                Object invoke = clazz.getMethod("getValue").invoke(v);
                if (invoke.toString().equalsIgnoreCase(value.toString())) {
                    return v;
                }
            }
        } catch (NoSuchMethodException e) {
            System.err.println("enum " + clazz.getCanonicalName() + " does not have method [getValue]");
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("enum " + clazz.getCanonicalName() + " does not have value " + value);
        } catch (Exception e) {
            System.err.println("unknown exception " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
