package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.ProjectTableEntity;


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

