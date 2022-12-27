package com.ustc.webide.admin.module.app.service.impl;

import com.ustc.webide.admin.module.app.dao.UserTableDao;
import com.ustc.webide.admin.module.app.entity.UserTableEntity;
import com.ustc.webide.admin.module.app.service.UserTableService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.webide.admin.utils.PageUtils;
import com.ustc.webide.admin.utils.Query;




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