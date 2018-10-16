package com.demo.common.entity;

import java.io.Serializable;
import java.util.List;

public class TreeNode implements TreeData<TreeNode>,Serializable{
    private String id;

    private String parentId;

    private String label;

    private List<TreeNode> children;

    private String isCheck;

    private String remarks;

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Override
    public String getIsCheck() {
        return isCheck;
    }

    @Override
    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}
