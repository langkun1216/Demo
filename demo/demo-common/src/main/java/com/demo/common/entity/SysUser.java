package com.demo.common.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class SysUser implements Serializable {
    private String id;

    //编号
    private String sysCode;

    //姓名
    private String sysName;

    //性别(1:男,2:女)
    private Integer sysSex;

    //帐号
    private String sysLoginName;

    //密码
    private String sysPassword;
}
