package com.xyl.study.controller;

import com.xyl.study.entity.User;
import com.xyl.study.service.RibbonHystrixService;
import com.xyl.study.service.UserFeignHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xyl on 10/31/17.
 */
@RestController
public class RibbonHystrixController {
    @Autowired
    private RibbonHystrixService ribbonHystrixService;
    @Autowired
    UserFeignHystrixClient userFeignHystrixClient;

    @GetMapping("/ribbon/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignHystrixClient.findByIdFeign(id);
//        return this.ribbonHystrixService.findById(id);
    }
}