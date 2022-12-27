package com.ustc.webide.admin.module.app.service.impl;

import com.ustc.webide.admin.module.app.dao.ProjectTableDao;
import com.ustc.webide.admin.module.app.entity.ProjectTableEntity;
import com.ustc.webide.admin.module.app.service.ProjectTableService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.webide.admin.utils.PageUtils;
import com.ustc.webide.admin.utils.Query;




@Service("projectTableService")
public class ProjectTableServiceImpl extends ServiceImpl<ProjectTableDao, ProjectTableEntity> implements ProjectTableService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProjectTableEntity> page = this.page(
                new Query<ProjectTableEntity>().getPage(params),
                new QueryWrapper<ProjectTableEntity>()
        );

        return new PageUtils(page);
    }

}