package com.ruoyi.papers.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.papers.domain.Papers;
import com.ruoyi.papers.service.IPapersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PapersControllerTest {

    @InjectMocks
    private PapersController papersController;

    @Mock
    private IPapersService papersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // 准备测试数据
        Papers papers = new Papers();
        List<Papers> expectedList = new ArrayList<>();
        expectedList.add(papers);

        // 模拟service行为
        when(papersService.selectPapersList(any(Papers.class))).thenReturn(expectedList);

        // 执行测试
        AjaxResult result = papersController.list(papers);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedList, result.get("data"));

        // 验证service方法被调用
        verify(papersService, times(1)).selectPapersList(any(Papers.class));
    }

    @Test
    void testListWithCategory() {
        // 准备测试数据
        Papers papers = new Papers();
        papers.setPapersCategory("测试类别");
        List<Papers> expectedList = new ArrayList<>();
        expectedList.add(papers);

        // 模拟service行为
        when(papersService.selectPapersWithChildren(any(Papers.class))).thenReturn(expectedList);

        // 执行测试
        AjaxResult result = papersController.list(papers);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedList, result.get("data"));

        // 验证service方法被调用
        verify(papersService, times(1)).selectPapersWithChildren(any(Papers.class));
    }

    @Test
    void testGetInfo() {
        // 准备测试数据
        Long papersId = 1L;
        Papers expectedPaper = new Papers();
        expectedPaper.setPapersId(papersId);

        // 模拟service行为
        when(papersService.selectPapersByPapersId(papersId)).thenReturn(expectedPaper);

        // 执行测试
        AjaxResult result = papersController.getInfo(papersId);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedPaper, result.get("data"));

        // 验证service方法被调用
        verify(papersService, times(1)).selectPapersByPapersId(papersId);
    }

    @Test
    void testAdd() {
        // 准备测试数据
        Papers papers = new Papers();
        papers.setPapersName("测试论文");

        // 模拟service行为
        when(papersService.insertPapers(any(Papers.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = papersController.add(papers);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("新增成功", result.get("msg"));

        // 验证service方法被调用
        verify(papersService, times(1)).insertPapers(any(Papers.class));
    }

    @Test
    void testEdit() {
        // 准备测试数据
        Papers papers = new Papers();
        papers.setPapersId(1L);
        papers.setPapersName("修改后的论文");

        // 模拟service行为
        when(papersService.updatePapers(any(Papers.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = papersController.edit(papers);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("修改成功", result.get("msg"));

        // 验证service方法被调用
        verify(papersService, times(1)).updatePapers(any(Papers.class));
    }

    @Test
    void testRemove() {
        // 准备测试数据
        Long[] papersIds = {1L, 2L};

        // 模拟service行为
        when(papersService.deletePapersByPapersIds(papersIds)).thenReturn(2);

        // 执行测试
        AjaxResult result = papersController.remove(papersIds);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("删除成功", result.get("msg"));

        // 验证service方法被调用
        verify(papersService, times(1)).deletePapersByPapersIds(papersIds);
    }
} 