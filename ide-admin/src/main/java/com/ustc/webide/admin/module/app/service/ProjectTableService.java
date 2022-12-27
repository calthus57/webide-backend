package com.ustc.webide.admin.module.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.webide.admin.utils.PageUtils;
import com.ustc.webide.admin.module.app.entity.ProjectTableEntity;


import java.util.Map;

/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-09-30 23:57:36
 */
public interface ProjectTableService extends IService<ProjectTableEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

