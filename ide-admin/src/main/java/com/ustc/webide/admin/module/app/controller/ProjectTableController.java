package com.ustc.webide.admin.module.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.ustc.webide.admin.module.app.entity.ProjectTableEntity;
import com.ustc.webide.admin.module.app.service.ProjectTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustc.webide.admin.utils.PageUtils;
import com.ustc.webide.admin.utils.R;



/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-09-30 23:57:36
 */
@RestController
@RequestMapping("ide-project/projecttable")
public class ProjectTableController {
    @Autowired
    private ProjectTableService projectTableService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectTableService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{projectId}")
    public R info(@PathVariable("projectId") Integer projectId){
		ProjectTableEntity projectTable = projectTableService.getById(projectId);

        return R.ok().put("projectTable", projectTable);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody ProjectTableEntity projectTable){
		projectTableService.save(projectTable);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody ProjectTableEntity projectTable){
		projectTableService.updateById(projectTable);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Integer[] projectIds){
		projectTableService.removeByIds(Arrays.asList(projectIds));

        return R.ok();
    }

}
