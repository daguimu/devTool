/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.utils;

import com.dagm.devtool.model.PageAssist;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分页索引计算相关
 *
 * @author: Guimu
 * @created: 2019/08/05
 */
//@UtilityClass
public class PageUtil {

    public PageAssist getPageAssist(Integer page, Integer pageSize) {
        long begin = (page - 1) * pageSize;
        long end = begin + pageSize - 1;
        return new PageAssist().setEnd(end).setStart(begin)
            .setValid(begin >= 0 && end >= 0);
    }

    /**
     * 根据total 总数获取每一页分页数据的 分页偏移量, 获取所有页
     */
    public List<PageAssist> getPageAssistForPageList(Integer pageSize, Long total) {
        long pageCount = total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1;
        checkPageCount(pageCount);
        return getPageAssistForPageList(pageSize, total, (int) pageCount);
    }

    /**
     * 根据total 总数获取每一页分页数据的 分页偏移量获取前pageNumber页
     */
    public List<PageAssist> getPageAssistForPageList(Integer pageSize, Long total,
        Integer pageNumber) {
        long pageCount = total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1;
        checkPageCount(pageCount);
        pageNumber = pageCount > pageNumber ? pageNumber : (int) pageCount;
        return Stream.iterate(1, x -> x + 1).limit(pageNumber).map(el -> {
            PageAssist pageAssist = getPageAssist(el, pageSize);
            pageAssist.setEnd(pageAssist.getEnd() >= total ? total - 1 : pageAssist.getEnd());
            return pageAssist;
        })
            .collect(
                Collectors.toList());
    }


    private void checkPageCount(long pageCount) {
        Preconditions
            .checkArgument(pageCount >= Integer.MIN_VALUE && pageCount <= Integer.MAX_VALUE,
                "页码参数过大");
    }
}