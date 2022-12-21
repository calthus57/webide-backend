package io.renren.modules.app.service.impl;

import io.renren.modules.app.dao.ProjectTableDao;
import io.renren.modules.app.entity.ProjectTableEntity;
import io.renren.modules.app.service.ProjectTableService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




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