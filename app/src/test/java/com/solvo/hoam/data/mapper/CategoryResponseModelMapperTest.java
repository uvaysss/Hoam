package com.solvo.hoam.data.mapper;

import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.network.response.Category;
import com.solvo.hoam.data.network.response.CategoryResponse;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryResponseModelMapperTest {

    @Mock
    private List<Category> categoryList;

    @Test
    public void map() throws Exception {
        // prepare
        CategoryResponseModelMapper categoryResponseModelMapper = new CategoryResponseModelMapper();
        Category category = new Category(
                "test_id",
                "test_name",
                "test_slug",
                "test_parent_id",
                0,
                "test_created_at",
                "test_updated_at"
        );

        // test
        CategoryModel categoryModel = categoryResponseModelMapper.map(category);

        // assertions
        assertEquals("test_id", categoryModel.getId());
        assertEquals("test_name", categoryModel.getName());
        assertEquals("test_slug", categoryModel.getSlug());
        assertEquals("test_parent_id", categoryModel.getParentId());
        assertEquals(0, categoryModel.getPriority());
        assertEquals("test_created_at", categoryModel.getCreatedAt());
        assertEquals("test_updated_at", categoryModel.getUpdatedAt());
    }

    @Test
    public void map1() throws Exception {
        // prepare
        CategoryResponseModelMapper categoryResponseModelMapper = new CategoryResponseModelMapper();
        CategoryResponse categoryResponse = new CategoryResponse(
                "test_id",
                "test_name",
                "test_slug",
                "test_parent_id",
                0,
                "test_created_at",
                "test_updated_at",
                categoryList
        );

        // test
        CategoryModel categoryModel = categoryResponseModelMapper.map(categoryResponse);

        // assertions
        assertEquals("test_id", categoryModel.getId());
        assertEquals("test_name", categoryModel.getName());
        assertEquals("test_slug", categoryModel.getSlug());
        assertEquals("test_parent_id", categoryModel.getParentId());
        assertEquals(0, categoryModel.getPriority());
        assertEquals("test_created_at", categoryModel.getCreatedAt());
        assertEquals("test_updated_at", categoryModel.getUpdatedAt());
    }

}