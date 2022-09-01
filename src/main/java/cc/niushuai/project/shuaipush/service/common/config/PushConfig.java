package cc.niushuai.project.shuaipush.service.common.config;

import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.common.util.CommonUtil;
import cc.niushuai.project.shuaipush.common.util.ValidatorUtil;
import cc.niushuai.project.shuaipush.service.common.enums.AvailableServiceEnum;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 推送配置
 *
 * @author niushuai
 * @date 2022/8/5 13:44
 */
@Slf4j
@Data
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(prefix = "push")
public class PushConfig {

    private String sendKey;

    private String enableService;

    private WeworkConfig wework;

    private WxConfig weixin;

    @PostConstruct
    public void init() {
        this.verify(enableService);
        log.info("配置文件加载成功");
    }

    /**
     * 校验所需参数是否启用
     *
     * @param enableService 启用的服务
     * @author niushuai
     * @date: 2022/9/1 16:28
     */
    public void verify(String enableService) {
        CommonUtil.nullable(this.getSendKey(), "请配置SendKey");

        for (String service : enableService.split(StrPool.COMMA)) {
            AvailableServiceEnum serviceEnum = AvailableServiceEnum.match(service);
            log.info("启用的服务: " + serviceEnum.name());
            // 校验所启用服务所需要的参数是否齐全
            switch (serviceEnum) {
                case Weixin:
                    this.verifyWeixin();
                    break;
                case Wework:
                    this.verifyWework();
                    break;
                case All:
                    this.verifyWeixin();
                    this.verifyWework();
                    return;
            }
        }
    }

    /**
     * 校验企业微信参数
     *
     * @author niushuai
     * @date: 2022/9/1 16:28
     */
    private void verifyWework() {
        CommonUtil.nullable(wework, "请补充企业微信应用参数");
        this.verify(wework);
    }

    /**
     * 校验微信公众号参数
     *
     * @author niushuai
     * @date: 2022/9/1 16:28
     */
    private void verifyWeixin() {
        CommonUtil.nullable(weixin, "请补充微信公众号参数");
        this.verify(weixin);
    }

    /**
     * 验证配置文件准确性
     *
     * @param <T> 待校验对象
     * @author niushuai
     * @date: 2022/8/5 14:01
     */
    public <T> void verify(T obj) {
        String validate = ValidatorUtil.validate(obj);
        if (StrUtil.isNotEmpty(validate)) {
            throw new BusinessException(validate);
        }
    }
}
