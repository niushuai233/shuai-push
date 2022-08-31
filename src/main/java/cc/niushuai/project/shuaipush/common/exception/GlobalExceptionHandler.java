package cc.niushuai.project.shuaipush.common.exception;

import cc.niushuai.project.shuaipush.common.base.Result;
import cc.niushuai.project.shuaipush.common.base.ResultCode;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 全局异常处理器
 *
 * @author niushuai
 * @date 2022/4/1 10:38
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常处理
     *
     * @param businessException 自定义抛出异常对象
     * @author niushuai
     * @date: 2022/4/1 11:11
     * @return: {@link Result <Object>} 接口返回内容
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Object> businessExceptionHandler(BusinessException businessException) {
        log.error(businessException.getMessage(), businessException);
        return Result.error(businessException.getCode(), businessException.getMessage());
    }

    /**
     * 请求方式不匹配异常处理
     *
     * @param exception
     * @author niushuai
     * @date: 2022/4/1 11:19
     * @return: {@link Result<?>}
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error(exception.getMessage(), exception);
        return Result.error(ResultCode.Http.Method_Not_Allowed, "请求方式不匹配");
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        StringBuilder message = new StringBuilder();
        allErrors.forEach(error -> message.append("[" + error.getDefaultMessage() + "], "));

        String trim = message.toString().trim();
        if (trim.endsWith(StrPool.COMMA)) {
            trim = trim.substring(0, trim.length() - 1);
        }

        return Result.error(trim);
    }

    /**
     * 兜底的异常处理器
     *
     * @param exception
     * @author niushuai
     * @date: 2022/4/1 11:18
     * @return: {@link Result<?>}
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return Result.error("系统异常");
    }

    @PostConstruct
    public void init() {
        log.info("Init Global Exception Handler Success.");
    }
}
