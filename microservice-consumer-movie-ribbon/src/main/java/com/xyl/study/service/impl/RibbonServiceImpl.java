package com.xyl.study.service.impl;

import com.xyl.study.entity.User;
import com.xyl.study.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xyl on 10/30/17.
 */
@Service
public class RibbonServiceImpl implements RibbonService {
    @Autowired
    private RestTemplate restTemplate;

    public User findById(Long id) {
        // http://服务提供者的serviceId/url
        return this.restTemplate.getForObject("http://MICROSERVICE-PROVIDER-USER/" + id, User.class);
    }
}
