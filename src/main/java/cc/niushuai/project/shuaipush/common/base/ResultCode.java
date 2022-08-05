package cc.niushuai.project.shuaipush.common.base;

/**
 * 响应码
 *
 * @author niushuai
 * @date 2022/8/5 9:26
 */
public interface ResultCode {

    interface Common {
        int SUCCESS = 0;
        String SUCCESS_MESSAGE = "message";
        int SERVER_INTERVAL_EXCEPTION = 500;
        String SERVER_INTERVAL_EXCEPTION_MESSAGE = "系统异常";
    }

    interface Http {
        int Method_Not_Allowed = 405;
    }
}
