package com.xyl.study.demoted;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Created by xyl on 11/6/17.
 */
@Slf4j
public class CoreHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        log.info("CoreHttpRequestInterceptor 执行");
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);

        String header = StringUtils.collectionToDelimitedString(
                CoreHeaderInterceptor.label.get(),
                CoreHeaderInterceptor.HEADER_LABEL_SPLIT);
        log.info("label: " + header);
        requestWrapper.getHeaders().add(CoreHeaderInterceptor.HEADER_LABEL, header);

        return execution.execute(requestWrapper, body);
    }
}
