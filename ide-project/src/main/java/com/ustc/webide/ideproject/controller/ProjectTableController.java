package com.ustc.webide.ideproject.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.common.utils.R;
import com.ustc.webide.ideproject.entity.UserTableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustc.webide.ideproject.entity.ProjectTableEntity;
import com.ustc.webide.ideproject.service.ProjectTableService;

import javax.websocket.server.PathParam;


/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-10-09 12:33:56
 */
@RestController
@RequestMapping("ideproject/projecttable")
public class ProjectTableController {
    @Autowired
    private ProjectTableService projectTableService;

    /**
     * 列表
     */
    @RequestMapping("/getProjectTableByUid")
    public R getProjectTableByUid(@RequestParam("userId") Integer userId){
        List<ProjectTableEntity> projectByUid = projectTableService.findProjectByUid(userId);
        return R.ok().put("projectList",projectByUid);
    }
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectTableService.queryPage(params);

        return R.ok().put("page", page);
    }
    @RequestMapping("/getProjectListByUserId")
    public R getProjectListByUserId(@RequestBody UserTableEntity userTableEntity){
        if(userTableEntity.getUserId()==null)return R.error("The userId is null!");
        List<ProjectTableEntity> projectTableEntities = projectTableService.selectAllProjectByUserId(userTableEntity.getUserId());
        //System.out.println(userTableEntity.getUserId());
        return R.ok().put("projectList",projectTableEntities);
    }
    @RequestMapping("/getRecentTemplateByUid")
    public R getRecentTemplateByUid(@RequestParam("userId")Integer id){

        List<ProjectTableEntity> list = projectTableService.getRecentTemplateByUid(id);
        return R.ok().put("recentProjectList",list);
    }
    @RequestMapping("/getRecentProjectId")
    public R getRecentProjectId(@RequestParam("userId")Integer id){
       // System.out.println(id);
        Integer resid = projectTableService.getRecentEiditProjectByUserId(id);
        return R.ok().put("recentProjectId",resid);
    }
    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@PathParam("projectId") Integer projectId){
		ProjectTableEntity projectTable = projectTableService.getById(projectId);
        return R.ok().put("projectTable", projectTable);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ProjectTableEntity projectTable){
        projectTableService.save(projectTable);
        return R.ok().put("projectTable",projectTable);
    }
    @RequestMapping("/createNewProject")
    public R createNewProject(@RequestBody ProjectTableEntity projectTable){
        if(projectTable.getProjectName().equals(""))
            return R.error("Project name must not be null!");
        String s = projectTableService.createNewProject(projectTable);
        if(s==null)
            return R.ok().put("projectTable",projectTable);
        else return R.error(s);
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
    @RequestMapping("/deleteProjectAndFile")
    public R deleteProjectAndFile(@RequestBody ProjectTableEntity projectTableEntity){
        projectTableService.removeByProjectEntity(projectTableEntity);
        return R.ok();
    }
    @RequestMapping("/runProjectById")
    public R runProjectByid(@RequestParam("projectId")Integer projectId){
          projectTableService.runProjectById(projectId);
        return R.ok().put("msg","success");
    }

}
