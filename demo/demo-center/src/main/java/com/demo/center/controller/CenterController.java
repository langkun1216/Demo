package com.demo.center.controller;

import com.demo.center.config.RedisService;
import com.demo.common.entity.SysUser;
import com.demo.common.utils.MapUtils;
import com.demo.common.utils.UuidUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/demo-center")
public class CenterController {
    @Autowired
    private RedisService redisService ;

    @Value("${spring.redis.port}")
    private String profile;

    @PostMapping(value = "/center-getRedisValue", consumes = {"application/json"},produces = {"application/json"})
    @ApiOperation(value = "center-获取RedisValue", notes = "center-获取RedisValue")
    @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String")
    public Map<String,Object> getRedisValue(@RequestBody String key){
        Map<String,Object> map = new HashMap<>();
        map.put(key,redisService.get(key));
        return map;
    }

    @PostMapping(value = "/center-addRedisValue", consumes = {"application/json"},produces = {"application/json"})
    @ApiOperation(value = "center-添加RedisValue", notes = "center-添加RedisValue")
    @ApiImplicitParam(name = "redis", value = "redis", required = true, dataType = "Map")
    public Map<String,Object> addRedisValue(@RequestBody Map<String,Object> redis){
        Boolean flag = redisService.set(redis.get("key").toString(), redis.get("value"));
        Map<String,Object> map = new HashMap<>();
        map.put("flag", flag);
        return map;
    }

    @GetMapping("/get")
    public String get(){
        return profile;
    }

    @PostMapping(value = "/center-addUserRedis", consumes = {"application/json"},produces = {"application/json"})
    @ApiOperation(value = "center-添加UserRedis", notes = "center-添加UserRedis")
    @ApiImplicitParam(name = "sysUser", value = "用户", required = true, dataType = "SysUser")
    public Map<String,Object> addUserRedis(@RequestBody SysUser sysUser){
        if(StringUtils.isBlank(sysUser.getId())){
            sysUser.setId(UuidUtil.getUuid());
        }
        Boolean flag = redisService.set(sysUser.getId(), sysUser);
        Map<String,Object> map = new HashMap<>();
        map.put("flag", flag);
        return map;
    }

    @PostMapping(value = "/center-getUserList", consumes = {"application/json"},produces = {"application/json"})
    @ApiOperation(value = "center-getUserList", notes = "center-getUserList")
    @ApiImplicitParam(name = "sysUser", value = "用户", required = true, dataType = "SysUser")
    public List<SysUser> getUserList(@RequestBody SysUser sysUser){
        SysUser sysUserRedis = (SysUser)redisService.get(sysUser.getId());;
        SysUser sysUser1 = new SysUser();
        sysUser1.setId(UuidUtil.getUuid());
        if(sysUserRedis == null){
            sysUser.setId(UuidUtil.getUuid());
        }else{
            sysUser = sysUserRedis;
            sysUser.setId("redis:" + sysUserRedis.getId());
        }
        MapUtils.copyProperties(sysUser1, sysUser);
        List<SysUser> list = new ArrayList<>();
        list.add(sysUser);
        list.add(sysUser1);
        return list;
    }
}
