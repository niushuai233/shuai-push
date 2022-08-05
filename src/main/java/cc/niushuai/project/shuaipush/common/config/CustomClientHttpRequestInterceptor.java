package cc.niushuai.project.shuaipush.common.config;

import cn.hutool.core.io.IoUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse httpResponse = execution.execute(request, body);
        traceResponse(httpResponse);
        return httpResponse;
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        log.debug("===========================request begin================================================");
        log.debug("URI         : {}", request.getURI());
        log.debug("Method      : {}", request.getMethod());
        log.debug("Headers     : {}", request.getHeaders());
        log.debug("Request body: {}", IoUtil.readUtf8(IoUtil.toStream(body)));
        log.debug("==========================request end================================================");
    }

    @SneakyThrows
    private void traceResponse(ClientHttpResponse response) {
        log.debug("============================response begin==========================================");
        log.debug("Status code  : {}", response.getStatusCode());
        log.debug("Status text  : {}", response.getStatusText());
        log.debug("Headers      : {}", response.getHeaders());
        log.debug("Response body: {}", IoUtil.readUtf8(response.getBody()));
        log.debug("=======================response end=================================================");
    }
}
