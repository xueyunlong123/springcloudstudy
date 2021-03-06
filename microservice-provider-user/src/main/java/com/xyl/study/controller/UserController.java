package com.xyl.study.controller;

/**
 * Created by xyl on 10/30/17.
 */

import com.xyl.study.dao.UserRepository;
import com.xyl.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作用：
 * ① 测试服务实例的相关内容
 * ② 为后来的服务做提供
 */
@Slf4j@RestController
public class UserController{

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserRepository userRepository;

    /**
     * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
     *
     * @param id
     * @return user信息
     * @RequestMapping(value = "/id", method = RequestMethod.GET)
     * 类似的注解还有@PostMapping等等
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }

    /**
     * 测试网管zuul的标签路由
     *
     * @param label
     * @return
     */
    @GetMapping("/test")
    public String test(@RequestHeader("x-label") String label) {
        log.info("label: " + label);
//        String result = restTemplate.getForObject("http://provider/user", String.class);
        return label;
    }

    /**
     * 本地服务实例的信息
     *
     * @return
     */
    @GetMapping("info")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }

}