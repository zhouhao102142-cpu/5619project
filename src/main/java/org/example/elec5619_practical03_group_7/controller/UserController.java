package org.example.elec5619_practical03_group_7.controller;

import io.swagger.annotations.Api;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.elec5619_practical03_group_7.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("api/user")
public class UserController {
    @Resource
    UserService userService;


}
