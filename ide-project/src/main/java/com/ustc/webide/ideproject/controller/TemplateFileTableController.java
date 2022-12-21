package com.ustc.webide.ideproject.controller;

import java.util.Arrays;
import java.util.Map;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.common.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustc.webide.ideproject.entity.TemplateFileTableEntity;
import com.ustc.webide.ideproject.service.TemplateFileTableService;


/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-10-14 10:24:32
 */
@RestController
@RequestMapping("ideproject/templatefiletable")
public class TemplateFileTableController {
    @Autowired
    private TemplateFileTableService templateFileTableService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = templateFileTableService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Integer id){
		TemplateFileTableEntity templateFileTable = templateFileTableService.getById(id);

        return R.ok().put("templateFileTable", templateFileTable);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody TemplateFileTableEntity templateFileTable){
		templateFileTableService.save(templateFileTable);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody TemplateFileTableEntity templateFileTable){
		templateFileTableService.updateById(templateFileTable);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Integer[] ids){
		templateFileTableService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
