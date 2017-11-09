package com.xyl.study.demoted;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Created by xyl on 11/6/17.
 */
public class DefaultRibbonConfiguration {
    @Value("${ribbon.client.name:#{null}}")
    private String name;

    @Autowired(required = false)
    private IClientConfig config;


    @Bean
    public IRule ribbonRule() {
//        if (StringUtils.isEmpty(name)) {
//            return null;
//        }

        // LWR 默认配置
        LabelAndWeightMetadataRule rule = new LabelAndWeightMetadataRule();
        rule.initWithNiwsConfig(config);
        return rule;
    }
}
