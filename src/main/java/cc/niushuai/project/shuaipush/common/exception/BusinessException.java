package cc.niushuai.project.shuaipush.common.exception;

import cc.niushuai.project.shuaipush.common.base.ResultCode;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义异常对象
 *
 * @author niushuai
 * @date 2022/4/1 10:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private int code;
    private String message;

    public BusinessException(String message) {
        this.code = ResultCode.Common.SERVER_INTERVAL_EXCEPTION;
        this.message = message;
    }

    public BusinessException(String message, Object... params) {
        this.code = ResultCode.Common.SERVER_INTERVAL_EXCEPTION;
        this.message = StrUtil.format(message, params);
    }
}
