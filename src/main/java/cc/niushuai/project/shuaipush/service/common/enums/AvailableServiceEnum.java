package cc.niushuai.project.shuaipush.service.common.enums;


/**
 * 支持的服务列表 可使用的
 *
 * @author niushuai
 * @date 2022/9/1 15:39
 */
public enum AvailableServiceEnum {

    Weixin, Wework, All;

    /**
     * 根据名称匹配可使用的服务列表
     *
     * @param name
     * @author niushuai
     * @date: 2022/9/1 15:40
     * @return: {@link AvailableServiceEnum} 可使用的服务列表
     */
    public static AvailableServiceEnum match(String name) {
        for (AvailableServiceEnum value : AvailableServiceEnum.values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return All;
    }
}
