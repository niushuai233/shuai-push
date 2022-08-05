package cc.niushuai.project.shuaipush.service.common;

import cc.niushuai.project.shuaipush.service.common.config.PushConfig;
import cc.niushuai.project.shuaipush.service.common.config.WeworkConfig;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;

/**
 * api常量
 *
 * @author niushuai
 * @date 2022/8/5 13:28
 */
public interface ApiConstant {

    String CORP_ID = "ID";
    String CORP_APP_ID = "CORP_APP_ID";
    String CORP_SECRET = "SECRET";
    String ACCESS_TOKEN = "ACCESS_TOKEN";

    /**
     * 企业微信相关接口
     */
    interface Wework {

        /**
         * 企业微信api基础路径
         */
        String Api_Base_Url = "https://qyapi.weixin.qq.com/cgi-bin";


        interface Keys {
            /**
             * 缓存key
             */
            String WEWORK_ACCESS_TOKEN = "wework_access_token";
        }

        /**
         * 认证相关
         */
        interface Auth {
            /**
             * 获取token
             */
            String Get_Token = "/gettoken?corpid=ID&corpsecret=SECRET";
        }

        /**
         * 消息相关
         */
        interface Message {

            /**
             * 发送应用消息
             */
            String Send = "/message/send?access_token=ACCESS_TOKEN";
        }
    }

    /**
     * 构建
     *
     * @param url         原地址
     * @param accessToken token
     * @author niushuai
     * @date: 2022/8/5 14:35
     * @return: {@link String}
     */
    static String constructWeworkUrl(String url, String accessToken) {
        if (StrUtil.isNotEmpty(accessToken)) {
            url = url.replace(ACCESS_TOKEN, accessToken);
        }
        WeworkConfig wework = SpringUtil.getBean(PushConfig.class).getWework();
        return Wework.Api_Base_Url + url.replace(CORP_ID, wework.getCorpId()).replace(CORP_SECRET, wework.getCorpSecret())
                .replace(CORP_APP_ID, wework.getAppId());
    }
}
