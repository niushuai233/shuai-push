package cc.niushuai.project.shuaipush.service.common.config;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 加载企业微信配置
 *
 * @author niushuai
 * @date 2022/8/5 13:56
 */
@Data
@Accessors(chain = true)
public class WeworkConfig {

    @NotEmpty(message = "企业ID不能为空")
    private String corpId;
    @NotEmpty(message = "企业秘钥不能为空")
    private String corpSecret;
    @NotNull(message = "企业应用ID不能为空")
    private Long appId;
    private String toUser;

}
