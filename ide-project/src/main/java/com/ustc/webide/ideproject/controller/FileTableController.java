package com.ustc.webide.ideproject.controller;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.common.utils.R;
import com.ustc.webide.ideproject.ResponseVo.ProjectContent;
import com.ustc.webide.ideproject.ResponseVo.ProjectVo;
import com.ustc.webide.ideproject.entity.ProjectTableEntity;
import com.ustc.webide.ideproject.service.ProjectTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustc.webide.ideproject.entity.FileTableEntity;
import com.ustc.webide.ideproject.service.FileTableService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-10-09 12:33:56
 */
@RestController
@RequestMapping("ideproject/filetable")
public class FileTableController {
    @Autowired
    private FileTableService fileTableService;

    @Autowired
    private ProjectTableService projectTableService;

    @RequestMapping("/test")
    public R test(){
        projectTableService.testProject();
        return R.ok().put("res","yes");
    }

    @RequestMapping("/phoneTest")
    public R phoneTest(@RequestParam("phone")String phone){
        System.out.println("电话号码："+phone);
        return R.ok().put("phone",phone);
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fileTableService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/getProjectById")
    public R getProjectById(@RequestParam("projectId")Integer id){
        ProjectVo data = fileTableService.getPageByProjectId(id);
        projectTableService.updateLastEiditTime(id);
        return R.ok().put("data",data);
    }
    @RequestMapping("/addNewFile")
    public R addNewFile(@RequestBody ProjectContent projectContent){
        //System.out.println(projectContent.getFileColumn().getFileName());
        String s = fileTableService.addNewFile(projectContent);
        if(s==null)
        return R.ok();
        else return  R.error(s);
    }
    @RequestMapping("/addNewFileFolder")
    public R addNewFileFolder(@RequestBody ProjectContent projectContent){
        String s = fileTableService.addNewFileFolder(projectContent);
        //System.out.println(projectContent.getFileColumn().getFileName()+projectContent.getFileColumn().getFileType());
        if(s==null)
            return R.ok();
        else return R.error(s);
    }
    @RequestMapping("/eiditFileName")
    public R eiditFileName(@RequestBody FileTableEntity fileTableEntity){
        if(fileTableEntity.getFileName()=="")return R.error("name must not be null!");
//        fileTableService.updateById(fileTableEntity);

         String s = fileTableService.updateFileName(fileTableEntity);
         if(s==null)
            return R.ok("success");
         else return R.error(s);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Integer id){
		FileTableEntity fileTable = fileTableService.getById(id);

        return R.ok().put("fileTable", fileTable);
    }
    @RequestMapping("/saveById")
    public R saveById(@RequestBody FileTableEntity file){
        fileTableService.saveByFileId(file);
        return R.ok();
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FileTableEntity fileTable){
		fileTableService.save(fileTable);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody FileTableEntity fileTable){
		fileTableService.updateById(fileTable);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		fileTableService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    @RequestMapping("/deleteFile")

    public R deleteFile(@RequestBody FileTableEntity fileTableEntity){
        String s = fileTableService.deleteFileFolder(fileTableEntity);
        if(s==null)
            return R.ok();
        else return R.error(s);
    }
    @RequestMapping("/deleteFileById")
    public R deleteFileById(@RequestBody FileTableEntity fileTableEntity){
        fileTableService.deleteFileById(fileTableEntity);
        return R.ok();
    }
    @RequestMapping("/updateNameById")
    public R updateNameById(@RequestBody FileTableEntity fileTableEntity){
        if(fileTableEntity.getFileName()!="")
        {
            fileTableService.updateById(fileTableEntity);
            return R.ok();
        }
        return R.error("File name must not be null!");
    }
    @RequestMapping("/createFileByTemplate")
    public R createFileByTemplate(@RequestBody ProjectTableEntity projectTableEntity){
        fileTableService.createFileByTemplate(projectTableEntity);
        return R.ok();
    }
    @RequestMapping("/downloadProject")
    public R downloadProject(HttpServletRequest httpServletRequest, HttpServletResponse response,@RequestParam("projectId")Integer projectId){
        String fileAndPackage = fileTableService.createFileAndPackage(projectId);
        String realPath = fileAndPackage;
        //String realPath="D:\\WebIDE\\ide\\userFileZIP\\312349.zip";
        // String realPath = this.getServletContext().getRealPath("/download/1.JPG");
        //2.获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("content-disposition", "attachment;filename="+fileName);
        //4.获取要下载的文件输入流
        InputStream in = null;
        try {
            in = new FileInputStream(realPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int len = 0;
        //5.创建数据缓冲区
        byte[] buffer = new byte[1024];
        //6.通过response对象获取OutputStream流
        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //7.将FileInputStream流写入到buffer缓冲区
        while (true) {
            try {
                if (!((len = in.read(buffer)) > 0)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
            try {
                out.write(buffer,0,len);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       // BufferedInputStream bins = new BufferedInputStream(fileAndPackage);//放到缓冲流里面
      //  OutputStream outs = response.getOutputStream();//获取文件输出IO流
       return R.ok().put("fileName",fileName);
    }

}
