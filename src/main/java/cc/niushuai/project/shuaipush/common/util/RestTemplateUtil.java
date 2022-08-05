package cc.niushuai.project.shuaipush.common.util;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * Http 请求工具类
 *
 * @author niushuai
 * @date 2022/8/5 14:56
 */
@Slf4j
public class RestTemplateUtil {

    private static RestTemplate restTemplate;

    static {
        restTemplate = SpringUtil.getBean(RestTemplate.class);
        log.info("加载RestTemplate客户端成功");
    }

    public static <T> T get(String url, Class<T> clazz) {
        return restTemplate.getForObject(url, clazz);
    }

    public static <T> T post(String url, Object request, Class<T> clazz) {
        return restTemplate.postForObject(url, request, clazz);
    }
}
