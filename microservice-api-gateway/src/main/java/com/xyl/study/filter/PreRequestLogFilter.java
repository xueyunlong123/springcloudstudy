package com.xyl.study.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xyl.study.zuul.filter.ZuulFilterType;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xyl on 11/3/17.
 */
@Slf4j
public class PreRequestLogFilter extends ZuulFilter{
    //过滤器类型：pre、route、post、error
    @Override
    public String filterType() {
        return ZuulFilterType.PRE.getValue();
    }

    //同一类型过滤器的执行顺序
    @Override
    public int filterOrder() {
        return 1;
    }

    //该过滤器是否执行
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }
}
