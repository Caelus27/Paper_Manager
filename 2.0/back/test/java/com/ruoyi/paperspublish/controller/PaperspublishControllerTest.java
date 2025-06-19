package com.ruoyi.paperspublish.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.paperspublish.domain.Paperspublish;
import com.ruoyi.paperspublish.service.IPaperspublishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaperspublishControllerTest {

    @InjectMocks
    private PaperspublishController paperspublishController;

    @Mock
    private IPaperspublishService paperspublishService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // 准备测试数据
        Paperspublish paperspublish = new Paperspublish();
        List<Paperspublish> expectedList = new ArrayList<>();
        expectedList.add(paperspublish);

        // 模拟service行为
        when(paperspublishService.selectPaperspublishList(any(Paperspublish.class))).thenReturn(expectedList);

        // 执行测试
        AjaxResult result = paperspublishController.list(paperspublish);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedList, result.get("data"));

        // 验证service方法被调用
        verify(paperspublishService, times(1)).selectPaperspublishList(any(Paperspublish.class));
    }

    @Test
    void testGetInfo() {
        // 准备测试数据
        Long papersId = 1L;
        Paperspublish expectedPaper = new Paperspublish();
        expectedPaper.setPapersId(papersId);

        // 模拟service行为
        when(paperspublishService.selectPaperspublishByPapersId(papersId)).thenReturn(expectedPaper);

        // 执行测试
        AjaxResult result = paperspublishController.getInfo(papersId);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedPaper, result.get("data"));

        // 验证service方法被调用
        verify(paperspublishService, times(1)).selectPaperspublishByPapersId(papersId);
    }

    @Test
    void testAdd() {
        // 准备测试数据
        Paperspublish paperspublish = new Paperspublish();
        paperspublish.setPapersName("测试论文");

        // 模拟service行为
        when(paperspublishService.insertPaperspublish(any(Paperspublish.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = paperspublishController.add(paperspublish);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("新增成功", result.get("msg"));

        // 验证service方法被调用
        verify(paperspublishService, times(1)).insertPaperspublish(any(Paperspublish.class));
    }

    @Test
    void testEdit() {
        // 准备测试数据
        Paperspublish paperspublish = new Paperspublish();
        paperspublish.setPapersId(1L);
        paperspublish.setPapersName("修改后的论文");

        // 模拟service行为
        when(paperspublishService.updatePaperspublish(any(Paperspublish.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = paperspublishController.edit(paperspublish);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("修改成功", result.get("msg"));

        // 验证service方法被调用
        verify(paperspublishService, times(1)).updatePaperspublish(any(Paperspublish.class));
    }

    @Test
    void testRemove() {
        // 准备测试数据
        Long[] papersIds = {1L, 2L};

        // 模拟service行为
        when(paperspublishService.deletePaperspublishByPapersIds(papersIds)).thenReturn(2);

        // 执行测试
        AjaxResult result = paperspublishController.remove(papersIds);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("删除成功", result.get("msg"));

        // 验证service方法被调用
        verify(paperspublishService, times(1)).deletePaperspublishByPapersIds(papersIds);
    }
} 