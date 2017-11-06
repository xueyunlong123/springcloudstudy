package com.xyl.study.demoted;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by xyl on 11/6/17.
 */
@Slf4j
public class CoreHeaderInterceptor extends HandlerInterceptorAdapter {

    @Value(value = "HEADER_LABEL")
    public static String HEADER_LABEL;
    @Value(value = "HEADER_LABEL_SPLIT")
    public static String HEADER_LABEL_SPLIT;

    public static final HystrixRequestVariableDefault<List<String>> label = new HystrixRequestVariableDefault<>();

    public static void initHystrixRequestContext(String labels) {
        log.info("label: " + labels);
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }

        if (!StringUtils.isEmpty(labels)) {
            CoreHeaderInterceptor.label.set(Arrays
                    .asList(labels.split(CoreHeaderInterceptor.HEADER_LABEL_SPLIT)));
        }
        else {
            CoreHeaderInterceptor.label.set(Collections.emptyList());
        }
    }

    public static void shutdownHystrixRequestContext() {
        if (HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.getContextForCurrentThread().shutdown();
        }
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        CoreHeaderInterceptor.initHystrixRequestContext(
                request.getHeader(CoreHeaderInterceptor.HEADER_LABEL));
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        CoreHeaderInterceptor.shutdownHystrixRequestContext();
    }
}