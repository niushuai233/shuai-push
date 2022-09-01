package cc.niushuai.project.shuaipush.service.common.config;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 微信配置
 *
 * @author niushuai
 * @date 2022/9/1 15:31
 */
@Data
@Accessors(chain = true)
public class WxConfig {

    /**
     * 公众号id
     */
    @NotEmpty(message = "公众号ID不能为空")
    private String appId;

    /**
     * 公众号秘钥
     */
    @NotEmpty(message = "公众号秘钥不能为空")
    private String appSecret;

    public void verify() {

    }
}
