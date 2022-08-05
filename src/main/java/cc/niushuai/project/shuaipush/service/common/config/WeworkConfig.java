package cc.niushuai.project.shuaipush.service.common.config;

import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.common.util.ValidatorUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 加载企业微信配置
 *
 * @author niushuai
 * @date 2022/8/5 13:56
 */
@Data
@Accessors(chain = true)
public class WeworkConfig {

    @NotBlank(message = "企业ID不能为空")
    private String corpId;
    @NotBlank(message = "企业秘钥不能为空")
    private String corpSecret;
    @NotBlank(message = "企业应用ID不能为空")
    private String appId;
    private String toUser;

    /**
     * 验证配置文件准确性
     *
     * @author niushuai
     * @date: 2022/8/5 14:01
     */
    public void verify() {
        String validate = ValidatorUtil.validate(this);
        if (StrUtil.isNotEmpty(validate)) {
            throw new BusinessException(validate);
        }
    }
}
