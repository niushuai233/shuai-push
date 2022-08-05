package cc.niushuai.project.shuaipush.common.util;

import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cn.hutool.core.util.StrUtil;

/**
 * 公共工具类
 *
 * @author niushuai
 * @date 2022/8/5 14:01
 */
public class CommonUtil {

    public static void nullable(Object data, String message) {
        if (null == data || (data instanceof String && StrUtil.isEmpty(data.toString()))) {
            throw new BusinessException(message);
        }
    }
}
