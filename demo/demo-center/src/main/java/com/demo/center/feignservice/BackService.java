package com.demo.center.feignservice;

import com.demo.common.entity.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "demo-back")
public interface BackService {

    @PostMapping(value = "/sysUser/login")
    @ApiOperation(value = "系统用户登入", notes = "系统用户登入")
    @ApiImplicitParam(name = "sysUser", value = "系统用户", required = true, dataType = "SysUser")
    SysUser login(@RequestBody SysUser sysUser);
}
