package com.demo.common.utils;

import com.demo.common.entity.TreeData;
import com.demo.common.entity.TreeNode;

import java.util.*;

/**
 * Created by Michae1 on 2018/5/31.
 */
public class TreeUtil {

    //获取顶层节点
    public static <T extends TreeData<T>> List<T> getTreeList(String topId,List<T> entityList){
        List<T> resultList = new ArrayList<>();//存储顶层的数据

        Map<Object, T> treeMap = new HashMap<>();
        T itemTree;

        for(int i=0;i<entityList.size()&&!entityList.isEmpty();i++) {
            itemTree = entityList.get(i);
            treeMap.put(itemTree.getId(),itemTree);//把所有的数据放到map当中，id为key
            if(topId.equals(itemTree.getParentId()) || itemTree.getParentId() == null) {//把顶层数据放到集合中
                resultList.add(itemTree);
            }
        }

        //循环数据，把数据放到上一级的childen属性中
        for(int i = 0; i< entityList.size()&&!entityList.isEmpty();i++) {
            itemTree = entityList.get(i);
            T data = treeMap.get(itemTree.getParentId());//在map集合中寻找父亲
            if(data != null) {//判断父亲有没有
                if(data.getChildren() == null) {
                    data.setChildren(new ArrayList<>());
                }
                data.getChildren().add(itemTree);//把子节点 放到父节点childList当中
                treeMap.put(itemTree.getParentId(), data);//把放好的数据放回map当中
            }
        }
        return resultList;
    }

    public static <T extends TreeData<T>> T setCheck(T treeData,String id){
        List<T> childTrees = new ArrayList<>();
        if (treeData.getId().equals(id)){
            treeData.setIsCheck("1");
            if(treeData.getChildren()!=null&& !treeData.getChildren().isEmpty()){
                for(T treeData1:treeData.getChildren()){
                    T treeData2 = setCheck(treeData1, id);
                    childTrees.add(treeData2);
                }
                treeData.setChildren(childTrees);
            }
            return treeData;
        } else {//没选中
            if(treeData.getChildren()!=null&& !treeData.getChildren().isEmpty()){
                for(T treeData1:treeData.getChildren()){
                    T treeData2 = setCheck(treeData1, id);
                    childTrees.add(treeData2);
                }
                treeData.setChildren(childTrees);
            }
            return treeData;
        }

    }

    /**
     * ListTreeNode去重
     *
     * @param list
     * @return
     */
    public static ArrayList<TreeNode> removeDuplicteNode(ArrayList<TreeNode> list) {
        Set<TreeNode> s = new TreeSet<TreeNode>(new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                if (null != o1.getParentId() && null == o2.getParentId() && o1.getId().compareTo(o2.getId()) == 0) {//不是重复数据
                    return -1;
                } else if (null != o2.getParentId() && null == o1.getParentId() && o1.getId().compareTo(o2.getId()) == 0) {
                    return -1;
                }
                return o1.getId().compareTo(o2.getId());
            }
        });
        s.addAll(list);
        return new ArrayList<TreeNode>(s);
    }
}
