package com.ustc.webide.ideproject.service;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.ideproject.ResponseVo.ProjectContent;
import com.ustc.webide.ideproject.ResponseVo.ProjectVo;
import com.ustc.webide.ideproject.entity.ProjectTableEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.webide.ideproject.entity.FileTableEntity;

import java.util.Map;

/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-10-09 12:33:56
 */
public interface FileTableService extends IService<FileTableEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ProjectVo getPageByProjectId(Integer id);

    void saveByFileId(FileTableEntity file);

    String addNewFile(ProjectContent projectContent);

    String addNewFileFolder(ProjectContent projectContent);

    String updateFileName(FileTableEntity fileTableEntity);

    String deleteFileFolder(FileTableEntity fileTableEntity);

    void deleteFileById(FileTableEntity fileTableEntity);

    void createFileByTemplate(ProjectTableEntity projectTableEntity);

    String createFileAndPackage(Integer projectId);
}

