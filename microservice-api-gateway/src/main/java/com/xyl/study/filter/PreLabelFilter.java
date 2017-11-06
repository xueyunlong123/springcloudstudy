package com.xyl.study.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xyl.study.demoted.CoreHeaderInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;

/**
 * Created by xyl on 11/6/17.
 */
@Slf4j
public class PreLabelFilter extends ZuulFilter {
    private static final HashMap<String, String> TOKEN_LABEL_MAP = new HashMap<>();

    static {
        TOKEN_LABEL_MAP.put("emt", "EN,Male,Test");
        TOKEN_LABEL_MAP.put("eft", "EN,Female,Test");
        TOKEN_LABEL_MAP.put("cmt", "CN,Male,Test");
        TOKEN_LABEL_MAP.put("cft", "CN,Female,Test");
        TOKEN_LABEL_MAP.put("em", "EN,Male");
        TOKEN_LABEL_MAP.put("ef", "EN,Female");
        TOKEN_LABEL_MAP.put("cm", "CN,Male");
        TOKEN_LABEL_MAP.put("cf", "CN,Female");
    }


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 获取请求中的标签，传递给后续的微服务中
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = ctx.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

        String labels = TOKEN_LABEL_MAP.get(token);

        log.info("label: " + labels);

        CoreHeaderInterceptor.initHystrixRequestContext(labels); // zuul本身调用微服务
        ctx.addZuulRequestHeader(CoreHeaderInterceptor.HEADER_LABEL, labels == null ? "":labels); // 传递给后续微服务

        return null;
    }
}
