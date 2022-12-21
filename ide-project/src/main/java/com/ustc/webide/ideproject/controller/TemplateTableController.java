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

import com.ustc.webide.ideproject.entity.TemplateTableEntity;
import com.ustc.webide.ideproject.service.TemplateTableService;


/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-10-14 10:24:32
 */
@RestController
@RequestMapping("ideproject/templatetable")
public class TemplateTableController {
    @Autowired
    private TemplateTableService templateTableService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = templateTableService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{templateId}")

    public R info(@PathVariable("templateId") Integer templateId){
		TemplateTableEntity templateTable = templateTableService.getById(templateId);

        return R.ok().put("templateTable", templateTable);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody TemplateTableEntity templateTable){
		templateTableService.save(templateTable);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody TemplateTableEntity templateTable){
		templateTableService.updateById(templateTable);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Integer[] templateIds){
		templateTableService.removeByIds(Arrays.asList(templateIds));

        return R.ok();
    }

}
