package io.renren.modules.app.service.impl;

import io.renren.modules.app.dao.UserTableDao;
import io.renren.modules.app.entity.UserTableEntity;
import io.renren.modules.app.service.UserTableService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("userTableService")
public class UserTableServiceImpl extends ServiceImpl<UserTableDao, UserTableEntity> implements UserTableService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserTableEntity> page = this.page(
                new Query<UserTableEntity>().getPage(params),
                new QueryWrapper<UserTableEntity>()
        );

        return new PageUtils(page);
    }

}