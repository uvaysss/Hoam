package com.solvo.hoam.data.db;

import com.solvo.hoam.data.network.response.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryLab {
    private static final String TAG = CategoryLab.class.getSimpleName();
    private static CategoryLab sCategoryLab = new CategoryLab();
    private List<Category> mParentCategoryList;
    private List<Category> mSubCategoryList;

    public static CategoryLab getInstance() {
        return sCategoryLab;
    }

    private CategoryLab() {
        mParentCategoryList = new ArrayList<>();
        mSubCategoryList = new ArrayList<>();
    }

    public List<Category> getParentCategories() {
        return mParentCategoryList;
    }

    public String getParentCategoryId(int position) {
        return  mParentCategoryList.get(position).getId();
    }

    public List<Category> getSubCategories(String parentId) {
        List<Category> subCategories = new ArrayList<>();

        subCategories.add(mSubCategoryList.get(0));

        for (Category category : mSubCategoryList) {
            if (category.getParentId().equals(parentId)) {
                subCategories.add(category);
            }
        }

        return subCategories;
    }

    public String getSubCategoryId(int position, String parentId) {
        return  getSubCategories(parentId).get(position).getId();
    }

    public void setCategories(List<Category> parentCategories, List<Category> subCategories) {
        mParentCategoryList = parentCategories;
        mSubCategoryList = subCategories;
    }

    public String getCategory(String categoryId) {
        for (Category category : mParentCategoryList) {
            if (category.getId().equals(categoryId)) {
                return category.getName();
            }
        }

        for (Category category : mSubCategoryList) {
            if (category.getId().equals(categoryId)) {
                return category.getName();
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return mParentCategoryList.size() == 0 || mSubCategoryList.size() == 0;

    }
}
