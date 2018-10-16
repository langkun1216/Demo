package com.demo.back.localservice;

import com.demo.common.entity.SysUser;

public interface SysUserService {

    SysUser login(SysUser sysUser);

    String getRole(String name);
}
