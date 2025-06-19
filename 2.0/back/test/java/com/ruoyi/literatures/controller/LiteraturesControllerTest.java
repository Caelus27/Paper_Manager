package com.ruoyi.literatures.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.literatures.domain.Literatures;
import com.ruoyi.literatures.service.ILiteraturesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LiteraturesControllerTest {

    @InjectMocks
    private LiteraturesController literaturesController;

    @Mock
    private ILiteraturesService literaturesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // 准备测试数据
        Literatures literatures = new Literatures();
        List<Literatures> expectedList = new ArrayList<>();
        expectedList.add(literatures);

        // 模拟service行为
        when(literaturesService.selectLiteraturesList(any(Literatures.class))).thenReturn(expectedList);

        // 执行测试
        AjaxResult result = literaturesController.list(literatures);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedList, result.get("data"));

        // 验证service方法被调用
        verify(literaturesService, times(1)).selectLiteraturesList(any(Literatures.class));
    }

    @Test
    void testListWithCategory() {
        // 准备测试数据
        Literatures literatures = new Literatures();
        literatures.setLiteraturesCategory("测试类别");
        List<Literatures> expectedList = new ArrayList<>();
        expectedList.add(literatures);

        // 模拟service行为
        when(literaturesService.selectLiteraturesWithChildren(any(Literatures.class))).thenReturn(expectedList);

        // 执行测试
        AjaxResult result = literaturesController.list(literatures);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedList, result.get("data"));

        // 验证service方法被调用
        verify(literaturesService, times(1)).selectLiteraturesWithChildren(any(Literatures.class));
    }

    @Test
    void testGetInfo() {
        // 准备测试数据
        Long literaturesId = 1L;
        Literatures expectedLiterature = new Literatures();
        expectedLiterature.setLiteraturesId(literaturesId);

        // 模拟service行为
        when(literaturesService.selectLiteraturesByLiteraturesId(literaturesId)).thenReturn(expectedLiterature);

        // 执行测试
        AjaxResult result = literaturesController.getInfo(literaturesId);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedLiterature, result.get("data"));

        // 验证service方法被调用
        verify(literaturesService, times(1)).selectLiteraturesByLiteraturesId(literaturesId);
    }

    @Test
    void testAdd() {
        // 准备测试数据
        Literatures literatures = new Literatures();
        literatures.setLiteraturesName("测试文献");

        // 模拟service行为
        when(literaturesService.insertLiteratures(any(Literatures.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = literaturesController.add(literatures);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("新增成功", result.get("msg"));

        // 验证service方法被调用
        verify(literaturesService, times(1)).insertLiteratures(any(Literatures.class));
    }

    @Test
    void testEdit() {
        // 准备测试数据
        Literatures literatures = new Literatures();
        literatures.setLiteraturesId(1L);
        literatures.setLiteraturesName("修改后的文献");

        // 模拟service行为
        when(literaturesService.updateLiteratures(any(Literatures.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = literaturesController.edit(literatures);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("修改成功", result.get("msg"));

        // 验证service方法被调用
        verify(literaturesService, times(1)).updateLiteratures(any(Literatures.class));
    }

    @Test
    void testRemove() {
        // 准备测试数据
        Long[] literaturesIds = {1L, 2L};

        // 模拟service行为
        when(literaturesService.deleteLiteraturesByLiteraturesIds(literaturesIds)).thenReturn(2);

        // 执行测试
        AjaxResult result = literaturesController.remove(literaturesIds);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("删除成功", result.get("msg"));

        // 验证service方法被调用
        verify(literaturesService, times(1)).deleteLiteraturesByLiteraturesIds(literaturesIds);
    }
} 