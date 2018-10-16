package com.demo.common.entity;

import java.util.List;

public interface TreeData<T> {

    public String getId();

    public String getParentId();

    public String getIsCheck();
    public void setIsCheck(String isCheck);

    public void setChildren(List<T> childList);

    public List<T> getChildren();
}
