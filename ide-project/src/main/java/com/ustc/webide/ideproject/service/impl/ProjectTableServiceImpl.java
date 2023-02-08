package com.ustc.webide.ideproject.service.impl;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.common.utils.Query;
import com.ustc.webide.ideproject.Utils.StartWeb;
import com.ustc.webide.ideproject.dao.FileTableDao;
import com.ustc.webide.ideproject.entity.FileTableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ustc.webide.ideproject.dao.ProjectTableDao;
import com.ustc.webide.ideproject.entity.ProjectTableEntity;
import com.ustc.webide.ideproject.service.ProjectTableService;
import org.springframework.transaction.annotation.Transactional;

@Service("projectTableService")
public class ProjectTableServiceImpl extends ServiceImpl<ProjectTableDao, ProjectTableEntity> implements ProjectTableService {
    @Autowired
    ProjectTableDao projectTableDao;
    @Autowired
    FileTableDao fileTableDao;
    @Autowired
    ThreadPoolExecutor executor;
    private  boolean isRunWebServer = false;
    private  final String TEMPLATE_ADDR = System.getProperty("user.dir")+"\\userFile\\template" ;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProjectTableEntity> page = this.page(
                new Query<ProjectTableEntity>().getPage(params),
                new QueryWrapper<ProjectTableEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ProjectTableEntity> selectAllProjectByUserId(Integer userId) {
//        QueryWrapper<ProjectTableEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id",userId);
//        List<ProjectTableEntity> projectTableEntities = projectTableDao.selectList(queryWrapper);
        return projectTableDao.findAllProjectByUserIdDescLastEiditTime(userId);
    }

    @Override
    public void updateLastEiditTime(Integer id) {
        ProjectTableEntity projectTableEntity = projectTableDao.selectById(id);
        projectTableEntity.setLastEiditTime(new Date());
        projectTableDao.updateById(projectTableEntity);
    }

    @Override
    public Integer getRecentEiditProjectByUserId(Integer id) {
        ProjectTableEntity recentEiditProjectByUserId = projectTableDao.findRecentEiditProjectByUserId(id);
        return recentEiditProjectByUserId.getProjectId();
    }

    @Override
    public List<ProjectTableEntity> getRecentTemplateByUid(Integer id) {

        return  projectTableDao.findRecentTemplateByUid(id);

    }

    @Override
    public String createNewProject(ProjectTableEntity projectTable) {
        QueryWrapper<ProjectTableEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_name",projectTable.getProjectName());
        queryWrapper.eq("user_id",projectTable.getUserId());
        ProjectTableEntity projectTableEntity = projectTableDao.selectOne(queryWrapper);
        if(projectTableEntity==null){
            projectTable.setCreateTime(new Date());
            projectTableDao.insert(projectTable);
            return null;
        }
         return "Project named \'"+projectTable.getProjectName()+"\' has been created!";

    }

    @Override
    public List<ProjectTableEntity> findProjectByUid(Integer userId) {
        QueryWrapper<ProjectTableEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<ProjectTableEntity> list = projectTableDao.selectList(queryWrapper);
        return list;
    }
    @Transactional
    @Override
    public void removeByProjectEntity(ProjectTableEntity projectTableEntity) {
        Integer id = projectTableEntity.getProjectId();
        fileTableDao.deleteProjectFileList(id);
        projectTableDao.deleteById(id);
    }

    @Override
    public void runProjectById(Integer projectId) {
        ProjectTableEntity projectTableEntity = projectTableDao.selectById(projectId);
        List<String > deleteFilePath = new ArrayList<>();
        List<FileTableEntity> projectFileList= fileTableDao.getFileTableListByProjectId(projectTableEntity.getProjectId());
        String projectPath = System.getProperty("user.dir")+"\\userFile\\template";
        projectFileList.forEach(item->{
            String path= getParentPath(item,projectFileList);
            if(item.getFileType().equals("file")){
                makeFile(projectPath,path,item);
                deleteFilePath.add(projectPath+"\\"+path+item.getFileName());
              //  System.out.println(projectPath+"\\"+path+item.getFileName());
            }
            else if(item.getFileType().equals("package")){
                makeDir(projectPath,path);
                deleteFilePath.add(projectPath+"\\"+path+item.getFileName());
               // System.out.println(projectPath+"\\"+path+item.getFileName());
            }


            //fileTableDao.updateById(item);
        });
        if(!isRunWebServer){
            new StartWeb().start();
            isRunWebServer = true;
        }


        System.out.println("启动完成，可以返回");
    }
    @Override
    public void testProject(){
//        CompletableFuture.supplyAsync(()->{
//            System.out.println("子线程结束！");
//            return  Thread.currentThread().getId();
//        },executor);
//
//        System.out.println("主线程结束！");
    }
    String getParentPath(FileTableEntity entity,List<FileTableEntity> projectFileList){
        if(entity.getPid()==(int)0)return "";
        for(int i=0;i<projectFileList.size();++i){
            if(entity.getPid()==(int)projectFileList.get(i).getId()){
                return getParentPath(projectFileList.get(i),projectFileList)+projectFileList.get(i).getFileName()+"\\";
            }
        }
        return "";
    }
    private void makeFile(String projectRootPath,String path,FileTableEntity entity) {
        File file = new File(projectRootPath+'\\'+path);
        if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        try{//异常处理
            BufferedWriter bw=new BufferedWriter(new FileWriter(projectRootPath+'\\'+path+entity.getFileName()));

            bw.write(entity.getFiletext());
            bw.close();//一定要关闭文件
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void makeDir(String projectPath, String path) {
        File file = new File(projectPath+'\\'+path);
        if(!file.exists()){//如果文件夹不存在
            //System.out.println(projectPath+'\\'+path);
            file.mkdir();//创建文件夹
        }
    }
}