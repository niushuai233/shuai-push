package cc.niushuai.project.shuaipush.service.common.config;

import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 加载企业微信配置
 *
 * @author niushuai
 * @date 2022/8/5 13:56
 */
@Data
@Accessors(chain = true)
public class WeWorkConfig {

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
        Set<ConstraintViolation<WeWorkConfig>> validate = SpringUtil.getBean(Validator.class).validate(this, Default.class);
        if (!validate.isEmpty()) {
            String errMsg = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
            throw new BusinessException(errMsg);
        }
    }
}
