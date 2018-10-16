package com.demo.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUserExtend implements Serializable {
    //用户信息
    private SysUser sysUser;
    //用户Token
    private String  token;
    //用户角色
    //private List<NtspAppSysRole> roles;
    //用户菜单（树形）
    private List<TreeNode> treeMenus;
}
