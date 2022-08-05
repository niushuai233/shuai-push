package cc.niushuai.project.shuaipush.service.common.enums;

import cc.niushuai.project.shuaipush.common.base.BaseEnum;

/**
 * 消息类型
 *
 * @author niushuai
 * @date 2022/8/5 9:49
 */
public enum MessageTypeEnum implements BaseEnum<String, MessageTypeEnum> {
    /**
     * QQ频道
     */
    QQ_Channel("QQ_Channel", "QQ频道"),
    /**
     * 微信公众号
     */
    WeChat("WeChat", "微信公众号"),
    /**
     * 企业微信
     */
    WeWork("WeWork", "企业微信");

    /**
     * key
     */
    private String value;
    /**
     * 描述值
     */
    private String desc;

    MessageTypeEnum(String value, String desc) {
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
