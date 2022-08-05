package cc.niushuai.project.shuaipush.common.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 通用返回类
 *
 * @author niushuai
 * @date 2022/8/4 17:57
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    private int code;

    private String message;

    private T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(ResultCode.Common.SUCCESS_MESSAGE);
    }

    public static <T> Result success(String message) {
        return success(ResultCode.Common.SUCCESS, message);
    }

    public static <T> Result success(T data) {
        return success(ResultCode.Common.SUCCESS, ResultCode.Common.SUCCESS_MESSAGE, data);
    }

    public static <T> Result success(int code, String message) {
        return success(code, message, null);
    }

    public static <T> Result success(int code, String message, T data) {
        return new Result(code, message, data);
    }

    public static <T> Result<T> error() {
        return error(ResultCode.Common.SERVER_INTERVAL_EXCEPTION_MESSAGE);
    }

    public static <T> Result error(String message) {
        return error(ResultCode.Common.SERVER_INTERVAL_EXCEPTION, message);
    }

    public static <T> Result error(T data) {
        return error(ResultCode.Common.SERVER_INTERVAL_EXCEPTION, ResultCode.Common.SERVER_INTERVAL_EXCEPTION_MESSAGE, data);
    }

    public static <T> Result error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> Result error(int code, String message, T data) {
        return new Result(code, message, data);
    }

}
