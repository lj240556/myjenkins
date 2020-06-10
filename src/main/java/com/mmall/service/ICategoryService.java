package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * Created by lj
 */
public interface ICategoryService {
    //增加分类
    ServerResponse addCategory(String categoryName, Integer parentId);
    //修改分类的名称
    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
    //得到父分类下面的子分类
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    //递归查询本节点的id及孩子节点的id
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}
