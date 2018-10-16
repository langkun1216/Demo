package com.demo.back.controller;

import com.demo.back.config.RedisService;
import com.demo.back.localservice.SysUserService;
import com.demo.common.entity.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sysUser")
public class LoginController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SysUserService sysUserService;

    private Logger logger= LoggerFactory.getLogger(LoginController.class);

    @PostMapping(value = "/login", consumes = {"application/json"},produces = {"application/json"})
    @ApiOperation(value = "用户登录操作",notes = "用户登录操作")
    @ApiImplicitParam(name = "sysUser", value = "系统用户", required = true, dataType = "SysUser")
    public SysUser login(@RequestBody SysUser sysUser){
        SysUser sysUserResult = sysUserService.login(sysUser);
        return sysUserResult;
    }

}
