package cc.niushuai.project.shuaipush.service.common.enums;

/**
 * 消息类型枚举
 *
 * @author niushuai
 * @date 2022/8/5 18:30
 */
public enum MessageTypeEnum {

    /**
     * 文本消息
     */
    Text("text", "文本消息"),

    /**
     * markdown消息
     */
    Markdown("markdown", "markdown消息"),
    ;

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
