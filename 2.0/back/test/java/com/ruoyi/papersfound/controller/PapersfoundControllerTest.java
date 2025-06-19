package com.ruoyi.papersfound.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.papersfound.domain.Papersfound;
import com.ruoyi.papersfound.service.IPapersfoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PapersfoundControllerTest {

    @InjectMocks
    private PapersfoundController papersfoundController;

    @Mock
    private IPapersfoundService papersfoundService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // 准备测试数据
        Papersfound papersfound = new Papersfound();
        List<Papersfound> expectedList = new ArrayList<>();
        expectedList.add(papersfound);

        // 模拟service行为
        when(papersfoundService.selectPapersfoundList(any(Papersfound.class))).thenReturn(expectedList);

        // 执行测试
        AjaxResult result = papersfoundController.list(papersfound);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedList, result.get("data"));

        // 验证service方法被调用
        verify(papersfoundService, times(1)).selectPapersfoundList(any(Papersfound.class));
    }

    @Test
    void testListWithCategory() {
        // 准备测试数据
        Papersfound papersfound = new Papersfound();
        papersfound.setPapersCategory("测试类别");
        List<Papersfound> expectedList = new ArrayList<>();
        expectedList.add(papersfound);

        // 模拟service行为
        when(papersfoundService.selectPapersfoundWithChildren(any(Papersfound.class))).thenReturn(expectedList);

        // 执行测试
        AjaxResult result = papersfoundController.list(papersfound);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedList, result.get("data"));

        // 验证service方法被调用
        verify(papersfoundService, times(1)).selectPapersfoundWithChildren(any(Papersfound.class));
    }

    @Test
    void testGetInfo() {
        // 准备测试数据
        Long papersId = 1L;
        Papersfound expectedPaper = new Papersfound();
        expectedPaper.setPapersId(papersId);

        // 模拟service行为
        when(papersfoundService.selectPapersfoundByPapersId(papersId)).thenReturn(expectedPaper);

        // 执行测试
        AjaxResult result = papersfoundController.getInfo(papersId);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedPaper, result.get("data"));

        // 验证service方法被调用
        verify(papersfoundService, times(1)).selectPapersfoundByPapersId(papersId);
    }

    @Test
    void testAdd() {
        // 准备测试数据
        Papersfound papersfound = new Papersfound();
        papersfound.setPapersName("测试论文");

        // 模拟service行为
        when(papersfoundService.insertPapersfound(any(Papersfound.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = papersfoundController.add(papersfound);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("新增成功", result.get("msg"));

        // 验证service方法被调用
        verify(papersfoundService, times(1)).insertPapersfound(any(Papersfound.class));
    }

    @Test
    void testEdit() {
        // 准备测试数据
        Papersfound papersfound = new Papersfound();
        papersfound.setPapersId(1L);
        papersfound.setPapersName("修改后的论文");

        // 模拟service行为
        when(papersfoundService.updatePapersfound(any(Papersfound.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = papersfoundController.edit(papersfound);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("修改成功", result.get("msg"));

        // 验证service方法被调用
        verify(papersfoundService, times(1)).updatePapersfound(any(Papersfound.class));
    }

    @Test
    void testRemove() {
        // 准备测试数据
        Long[] papersIds = {1L, 2L};

        // 模拟service行为
        when(papersfoundService.deletePapersfoundByPapersIds(papersIds)).thenReturn(2);

        // 执行测试
        AjaxResult result = papersfoundController.remove(papersIds);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("删除成功", result.get("msg"));

        // 验证service方法被调用
        verify(papersfoundService, times(1)).deletePapersfoundByPapersIds(papersIds);
    }
} 