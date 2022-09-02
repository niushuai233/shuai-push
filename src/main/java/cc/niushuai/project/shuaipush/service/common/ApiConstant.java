package cc.niushuai.project.shuaipush.service.common;

import cc.niushuai.project.shuaipush.service.common.config.WeworkConfig;
import cc.niushuai.project.shuaipush.service.common.config.WxConfig;
import cn.hutool.core.util.StrUtil;

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
    String WX_APP_ID = "APPID";
    String WX_APP_SECRET = "APPSECRET";

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
            String ACCESS_TOKEN = "wework_access_token";

            /**
             * 发送给所有人时的值
             */
            String ALL_USER = "@all";
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

    interface Weixin {

        /**
         * 微信公众号api基础路径
         */
        String Api_Base_Url = "https://api.weixin.qq.com/cgi-bin";

        interface Keys {

            /**
             * 缓存key
             */
            String ACCESS_TOKEN = "weixin_access_token";
        }

        /**
         * 认证相关
         */
        interface Auth {
            /**
             * 获取token
             */
            String Get_Token = "/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        }

        /**
         * 消息相关
         */
        interface Message {

            /**
             * 发送模板消息
             */
            String Template_Send = "/message/template/send?access_token=ACCESS_TOKEN";
        }
    }

    /**
     * 构建完整的企业微信api地址
     *
     * @param url         原地址
     * @param accessToken token
     * @param wework      企业微信配置
     * @author niushuai
     * @date: 2022/8/5 14:35
     * @return: {@link String}
     */
    static String constructWeworkUrl(String url, String accessToken, WeworkConfig wework) {
        if (StrUtil.isNotEmpty(accessToken)) {
            url = url.replace(ACCESS_TOKEN, accessToken);
        }
        return Wework.Api_Base_Url + url.replace(CORP_ID, wework.getCorpId()).replace(CORP_SECRET, wework.getCorpSecret())
                .replace(CORP_APP_ID, wework.getAppId().toString());
    }

    /**
     * 构建完整的微信公众号api地址
     *
     * @param url         原地址
     * @param accessToken token
     * @param wxConfig    微信配置
     * @author niushuai
     * @date: 2022/9/1 17:09
     * @return: {@link String}
     */
    static String constructWeixinUrl(String url, String accessToken, WxConfig wxConfig) {
        if (StrUtil.isNotEmpty(accessToken)) {
            url = url.replace(ACCESS_TOKEN, accessToken);
        }
        return Weixin.Api_Base_Url + url.replace(WX_APP_ID, wxConfig.getAppId()).replace(WX_APP_SECRET, wxConfig.getAppSecret());
    }
}
