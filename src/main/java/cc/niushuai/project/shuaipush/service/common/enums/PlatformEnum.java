package cc.niushuai.project.shuaipush.service.common.enums;

import cc.niushuai.project.shuaipush.common.base.BaseEnum;

/**
 * 推送平台
 *
 * @author niushuai
 * @date 2022/8/5 9:49
 */
public enum PlatformEnum implements BaseEnum<String, PlatformEnum> {
    /**
     * QQ频道
     */
    QQ_Channel("QQ_Channel", "QQ频道"),
    /**
     * 微信公众号
     */
    Weixin("Weixin", "微信公众号"),
    /**
     * 企业微信
     */
    Wework("Wework", "企业微信"),
    /**
     * 全部来一遍
     */
    ALL("all", "全部来一遍");

    /**
     * key
     */
    private String value;
    /**
     * 描述值
     */
    private String desc;

    PlatformEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
