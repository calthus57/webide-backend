package com.ustc.webide.ideproject.service.impl;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.common.utils.Query;
import com.ustc.webide.ideproject.ResponseVo.ProjectContent;
import com.ustc.webide.ideproject.ResponseVo.ProjectVo;
import com.ustc.webide.ideproject.Utils.Ziputils;
import com.ustc.webide.ideproject.dao.ProjectTableDao;
import com.ustc.webide.ideproject.dao.TemplateFileTableDao;
import com.ustc.webide.ideproject.entity.ProjectTableEntity;
import com.ustc.webide.ideproject.entity.TemplateFileTableEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.ustc.webide.ideproject.dao.FileTableDao;
import com.ustc.webide.ideproject.entity.FileTableEntity;
import com.ustc.webide.ideproject.service.FileTableService;
import org.springframework.transaction.annotation.Transactional;


@Service("fileTableService")
public class FileTableServiceImpl extends ServiceImpl<FileTableDao, FileTableEntity> implements FileTableService {
    @Autowired
    FileTableDao fileTableDao;
    @Autowired
    TemplateFileTableDao templateFileTableDao;
    @Autowired
    ProjectTableDao projectTableDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FileTableEntity> page = this.page(
                new Query<FileTableEntity>().getPage(params),
                new QueryWrapper<FileTableEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ProjectVo getPageByProjectId(Integer id) {
        QueryWrapper<FileTableEntity> mapper = new QueryWrapper<>();
        mapper.eq("project_id",id);

        List<FileTableEntity> fileTableEntities = fileTableDao.selectList(mapper);

        ProjectVo projectVo = new ProjectVo();
        projectVo.setProject_id(id);
        List<ProjectContent> childsFilefolder = new ArrayList<>();
        List<ProjectContent> childsFile = new ArrayList<>();
        for(int i=0;i<fileTableEntities.size();++i){
            if(fileTableEntities.get(i).getPid()==0){
                if(fileTableEntities.get(i).getFileType().equals("package"))
                    childsFilefolder.add(new ProjectContent(fileTableEntities.get(i)));
                else childsFile.add(new ProjectContent(fileTableEntities.get(i)));
            }
        }
        for(int i=0;i<childsFile.size();++i){
            childsFilefolder.add(childsFile.get(i));
        };
        projectVo.setChildren(childsFilefolder);
        for(int i=0;i<projectVo.getChildren().size();++i){
            setAllChilds(projectVo.getChildren().get(i),fileTableEntities);
        }

        return  projectVo;
    }

    @Override
    public void saveByFileId(FileTableEntity file) {
        //System.out.println("id:"+file.getId()+"\nfiletext:"+file.getFiletext());
        FileTableEntity fileTableEntity = fileTableDao.selectById(file.getId());
        fileTableEntity.setFiletext(file.getFiletext());
        fileTableDao.updateById(fileTableEntity);
    }

    @Override
    public String addNewFile(ProjectContent projectContent) {
        FileTableEntity fileTableEntity = projectContent.getFileColumn();
        String fileName = projectContent.getFileColumn().getFileName();

        String [] strs = fileName.split("\\.");
        if(strs.length==1){
            projectContent.getFileColumn().setSuffix("text");
            projectContent.getFileColumn().setFileName(fileName+".text");
            projectContent.getFileColumn().setSuffix("text");
        }
        else projectContent.getFileColumn().setSuffix(strs[strs.length-1]);
        QueryWrapper<FileTableEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id",fileTableEntity.getProjectId());
        queryWrapper.eq("pid",fileTableEntity.getPid());
        queryWrapper.eq("file_name",fileTableEntity.getFileName());
        FileTableEntity fileTableEntity1 = fileTableDao.selectOne(queryWrapper);
        if(fileTableEntity1!=null)return "This file has been created!";
        fileTableDao.insert(fileTableEntity);
//        QueryWrapper<FileTableEntity>  queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("project_id",projectContent.getFileColumn().getProjectId());
//        List<FileTableEntity> fileTableEntities = fileTableDao.selectList(queryWrapper);
//        System.out.println(fileTableEntity.getPid()+" !"+fileTableEntities.size());
//        List<Integer> path = new ArrayList<>();
//        findPath(path,fileTableEntities,fileTableEntity.getPid());
//        return new PathToFolder(path,fileTableEntity);
        return null;
    }
    @Override
    public String addNewFileFolder(ProjectContent projectContent) {
        FileTableEntity fileTableEntity = projectContent.getFileColumn();
        QueryWrapper<FileTableEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id",fileTableEntity.getProjectId());
        queryWrapper.eq("pid",fileTableEntity.getPid());
        queryWrapper.eq("file_name",fileTableEntity.getFileName());
        FileTableEntity fileTableEntity1 = fileTableDao.selectOne(queryWrapper);
        if(fileTableEntity1==null){
            fileTableEntity.setSuffix("filefolder");
            fileTableDao.insert(fileTableEntity);
            return null;
        }
        else return "This filefolder has been created!";

    }
    @Override
    public String updateFileName(FileTableEntity fileTableEntity) {
        QueryWrapper<FileTableEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id",fileTableEntity.getProjectId());
        queryWrapper.eq("pid",fileTableEntity.getPid());
        queryWrapper.eq("file_name",fileTableEntity.getFileName());
        FileTableEntity fileTableEntity1 = fileTableDao.selectOne(queryWrapper);
        if(fileTableEntity1==null){
            if(fileTableEntity.getFileType().equals("file")){
                String [] strs = fileTableEntity.getFileName().split("\\.");
                if(strs.length>1)
                    fileTableEntity.setSuffix(strs[strs.length-1]);
                else {
                    fileTableEntity.setSuffix("text");
                    if(fileTableEntity.getFileName().contains("."))
                        fileTableEntity.setFileName(fileTableEntity.getFileName()+"text");
                    else
                        fileTableEntity.setFileName(fileTableEntity.getFileName()+".text");
                }
            }
            fileTableDao.updateById(fileTableEntity);
            return null;
        }
        if(fileTableEntity1.getId()==(int)fileTableEntity.getId())
            return null;
        else  return "This name has been used!";
    }
    @Override
    public String deleteFileFolder(FileTableEntity fileTableEntity) {
        if(fileTableEntity.getFileType().equals("package")){
            FileTableEntity entity = new FileTableEntity();
            entity.setProjectId(fileTableEntity.getProjectId());
            //entity.set
            List<Integer> ids = new ArrayList<>();
            QueryWrapper<FileTableEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id",fileTableEntity.getProjectId());
            List<FileTableEntity> fileTableEntities = fileTableDao.selectList(queryWrapper);
            Queue<FileTableEntity> que = new LinkedList<>();
            que.offer(fileTableEntity);
            while(!que.isEmpty()){
                FileTableEntity temp = que.poll();
                Integer deleteId = temp.getId();
                ids.add(deleteId);
                for(int i=0;i<fileTableEntities.size();++i){
                    if(deleteId==(int)fileTableEntities.get(i).getPid()){
                        que.offer(fileTableEntities.get(i));
                    }
                }
            }
            try {
                fileTableDao.deleteBatchIds(ids);
            }catch (Exception e){
                return "Unknow error!";
            }
            return null;
        }
        else if(fileTableEntity.getFileType().equals("file")){
            try {
                fileTableDao.deleteById(fileTableEntity.getId());
                return null;
            }catch (Exception e){
                return "Unknow error!";
            }
        }
        return null;
    }
    @Override
    public void deleteFileById(FileTableEntity fileTableEntity) {
        fileTableDao.deleteById(fileTableEntity.getId());
    }
    @Transactional
    @Override
    public void createFileByTemplate(ProjectTableEntity projectTableEntity) {
        QueryWrapper<TemplateFileTableEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("template_id",projectTableEntity.getTemplateId());
        List<TemplateFileTableEntity> templateFileTableEntities = templateFileTableDao.selectList(queryWrapper);
        List<FileTableEntity> fileTableEntities = new ArrayList<>();
        templateFileTableEntities.forEach(item->{
            fileTableEntities.add(copyTemplateToFile(new FileTableEntity(),item));
        });
        Queue<FileTableEntity> que = new LinkedList<>();
        fileTableEntities.forEach(item->{
            item.setProjectId(projectTableEntity.getProjectId());
            fileTableDao.insert(item);
            if(item.getPid()==0)
                que.offer(item);

        });
       while(!que.isEmpty()){
           FileTableEntity tempFile = que.poll();
           fileTableEntities.forEach(item->{
               if(tempFile.getVirtulId()==(int)item.getPid()){
                   item.setPid(tempFile.getId());//tempFile.getId()
                   que.offer(item);

               }
           });
       }
       fileTableEntities.forEach(item->{
           fileTableDao.updateById(item);
       });
    }
    @Transactional
    @Override
    public String createFileAndPackage(Integer projectId) {
        QueryWrapper<ProjectTableEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id",projectId);
        ProjectTableEntity projectTableEntity = projectTableDao.selectOne(queryWrapper);
        List<FileTableEntity> projectFileList= fileTableDao.getFileTableListByProjectId(projectId);
        String projectPath = System.getProperty("user.dir")+"\\userFile\\"+projectTableEntity.getProjectName()+projectTableEntity.getProjectId();
        projectFileList.forEach(item->{
            String path= getParentPath(item,projectFileList);
            if(item.getFileType().equals("file")){
                makeFile(projectPath,path,item);
                item.setFileAddr("\\userFile\\"+projectTableEntity.getProjectName()+projectTableEntity.getProjectId()+"\\"+path+item.getFileName());
            }
            else if(item.getFileType().equals("package")){
                makeDir(projectPath,path);
                item.setFileAddr("\\userFile"+"\\"+path);
            }

            fileTableDao.updateById(item);
        });
        //projectTableDao.
        /*
        *
        * */
        String zip = System.getProperty("user.dir")+"\\userFileZIP\\"+projectTableEntity.getProjectName()+projectTableEntity.getProjectId()+".zip";
        FileOutputStream fos1 = null;
        try {
            fos1 = new  FileOutputStream(new File(zip));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       boolean flag =  Ziputils.toZip(projectPath, fos1, true);

        if(flag){
            projectTableEntity.setProjectStruct(zip);
            projectTableDao.updateById(projectTableEntity);
            return projectTableEntity.getProjectStruct();
        }
        else return null;
        //Ziputils.toZip(projectPath,fos1,true);
    }

    private void makeDir(String projectPath, String path) {
        File file = new File(projectPath+'\\'+path);
        if(!file.exists()){//如果文件夹不存在
            //System.out.println(projectPath+'\\'+path);
            file.mkdir();//创建文件夹
        }
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
    private

    String getParentPath(FileTableEntity entity,List<FileTableEntity> projectFileList){
        if(entity.getPid()==(int)0)return "";
        for(int i=0;i<projectFileList.size();++i){
             if(entity.getPid()==(int)projectFileList.get(i).getId()){
                 return getParentPath(projectFileList.get(i),projectFileList)+projectFileList.get(i).getFileName()+"\\";
             }
         }
        return "";
    }
    FileTableEntity copyTemplateToFile(FileTableEntity fileTableEntity,TemplateFileTableEntity templateFileTableEntity){
        BeanUtils.copyProperties(templateFileTableEntity,fileTableEntity);
        fileTableEntity.setVirtulId(fileTableEntity.getId());
        fileTableEntity.setId(null);
        return fileTableEntity;
    }
//    public void findPath(List<Integer> path, List<FileTableEntity> fileTableEntities,Integer pid){
//        if(pid!=0){
//            path.add(pid);
//            for(int i=0;i<fileTableEntities.size();++i){
//                if(fileTableEntities.get(i).getId()==(int)pid){
//                    pid=fileTableEntities.get(i).getPid();
//                    findPath(path,fileTableEntities,pid);
//                }
//            }
//        }
//    }
    public void setAllChilds(ProjectContent projectContent,List<FileTableEntity> fileTableEntities){
        List<ProjectContent> childsFileFolder = new ArrayList<>();
        List<ProjectContent> childsFile = new ArrayList<>();
        for(int i=0;i<fileTableEntities.size();++i){
            if(fileTableEntities.get(i).getPid()==(int)projectContent.getFileColumn().getId()){
                if(fileTableEntities.get(i).getFileType().equals("package"))
                childsFileFolder.add(new ProjectContent(fileTableEntities.get(i)));
            else if(fileTableEntities.get(i).getFileType().equals("file"))
                    childsFile.add(new ProjectContent(fileTableEntities.get(i)));
            }

        }
        for(int i=0;i<childsFile.size();++i){
            childsFileFolder.add(childsFile.get(i));
        }
        projectContent.setChildren(childsFileFolder);
        for(int i=0;i<projectContent.getChildren().size();++i){
            setAllChilds(projectContent.getChildren().get(i),fileTableEntities);
        }
    }

}