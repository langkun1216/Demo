package com.demo.back.localservice.impl;

import com.demo.back.dao.SysUserMapper;
import com.demo.back.localservice.SysUserService;
import com.demo.common.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser login(SysUser sysUser) {
        return sysUserMapper.getSysUser(sysUser);
    }

    @Override
    public String getRole(String name) {
        return sysUserMapper.getRole(name);
    }
}
