package com.demo.back.dao;

import com.demo.common.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

    SysUser getSysUser(SysUser sysUser);

    
    String getRole(String name);
}
