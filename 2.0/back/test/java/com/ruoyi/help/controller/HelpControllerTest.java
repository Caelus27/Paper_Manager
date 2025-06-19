package com.ruoyi.help.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.help.domain.Help;
import com.ruoyi.help.service.IHelpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HelpControllerTest {

    @InjectMocks
    private HelpController helpController;

    @Mock
    private IHelpService helpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // 准备测试数据
        Help help = new Help();
        List<Help> expectedList = new ArrayList<>();
        expectedList.add(help);

        // 模拟service行为
        when(helpService.selectHelpList(any(Help.class))).thenReturn(expectedList);

        // 执行测试
        AjaxResult result = helpController.list(help);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedList, result.get("data"));

        // 验证service方法被调用
        verify(helpService, times(1)).selectHelpList(any(Help.class));
    }

    @Test
    void testGetInfo() {
        // 准备测试数据
        Long papersId = 1L;
        Help expectedHelp = new Help();
        expectedHelp.setPapersId(papersId);

        // 模拟service行为
        when(helpService.selectHelpByPapersId(papersId)).thenReturn(expectedHelp);

        // 执行测试
        AjaxResult result = helpController.getInfo(papersId);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals(expectedHelp, result.get("data"));

        // 验证service方法被调用
        verify(helpService, times(1)).selectHelpByPapersId(papersId);
    }

    @Test
    void testAdd() {
        // 准备测试数据
        Help help = new Help();
        help.setPapersName("测试论文");

        // 模拟service行为
        when(helpService.insertHelp(any(Help.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = helpController.add(help);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("新增成功", result.get("msg"));

        // 验证service方法被调用
        verify(helpService, times(1)).insertHelp(any(Help.class));
    }

    @Test
    void testEdit() {
        // 准备测试数据
        Help help = new Help();
        help.setPapersId(1L);
        help.setPapersName("修改后的论文");

        // 模拟service行为
        when(helpService.updateHelp(any(Help.class))).thenReturn(1);

        // 执行测试
        AjaxResult result = helpController.edit(help);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("修改成功", result.get("msg"));

        // 验证service方法被调用
        verify(helpService, times(1)).updateHelp(any(Help.class));
    }

    @Test
    void testRemove() {
        // 准备测试数据
        Long[] papersIds = {1L, 2L};

        // 模拟service行为
        when(helpService.deleteHelpByPapersIds(papersIds)).thenReturn(2);

        // 执行测试
        AjaxResult result = helpController.remove(papersIds);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("删除成功", result.get("msg"));

        // 验证service方法被调用
        verify(helpService, times(1)).deleteHelpByPapersIds(papersIds);
    }
} 