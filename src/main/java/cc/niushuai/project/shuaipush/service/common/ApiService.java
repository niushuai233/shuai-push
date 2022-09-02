package cc.niushuai.project.shuaipush.service.common;

import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.service.common.config.PushConfig;
import cc.niushuai.project.shuaipush.service.common.config.WeworkConfig;
import cc.niushuai.project.shuaipush.service.common.config.WxConfig;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;

/**
 * 抽象公共内容
 *
 * @author niushuai
 * @date 2022/9/1 15:27
 */
public interface ApiService {

    /**
     * 获取access_token 各个服务token是必须的
     *
     * @author niushuai
     * @date: 2022/9/1 16:57
     * @return: {@link String} access_token
     */
    String getAccessToken();

    /**
     * 配置的key
     *
     * @author niushuai
     * @date: 2022/9/1 15:30
     * @return: {@link String}
     */
    default String getSendKey() {
        return getPushConfig().getSendKey();
    }

    /**
     * 获取推送配置信息 总
     *
     * @author niushuai
     * @date: 2022/9/1 15:28
     * @return: {@link PushConfig}
     */
    default PushConfig getPushConfig() {
        return SpringUtil.getBean(PushConfig.class);
    }

    /**
     * 获取企业微信配置信息
     *
     * @author niushuai
     * @date: 2022/9/1 15:30
     * @return: {@link WeworkConfig} 企业微信应用配置
     */
    default WeworkConfig getWework() {
        return getPushConfig().getWework();
    }

    /**
     * 获取微信公众号配置信息
     *
     * @author niushuai
     * @date: 2022/9/1 16:43
     * @return: {@link WxConfig} 微信公众号配置
     */
    default WxConfig getWeixin() {
        return getPushConfig().getWeixin();
    }

    /**
     * 统一处理response
     *
     * @param title 消息类型
     * @param t     返回对象
     * @author niushuai
     * @date: 2022/9/2 9:39
     */
    default <T> void dealResponse(String title, T t) {
        boolean isSuccess = false;
        Object errorCode, errorMessage;
        try {
            isSuccess = (boolean) ReflectUtil.getMethodByName(t.getClass(), "isSuccess").invoke(t);
            errorCode = ReflectUtil.getFieldValue(t, "errorCode");
            errorMessage = ReflectUtil.getFieldValue(t, "errorMessage");
        } catch (Exception e) {
            throw new BusinessException("处理响应结果异常: {}", e.getMessage());
        }
        if (!isSuccess) {
            throw new BusinessException(title + ", 错误码:[" + errorCode + "], " + errorMessage);
        }
    }
}
