package com.ustc.webide.ideproject.service.impl;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.ustc.webide.ideproject.dao.TemplateTableDao;
import com.ustc.webide.ideproject.entity.TemplateTableEntity;
import com.ustc.webide.ideproject.service.TemplateTableService;


@Service("templateTableService")
public class TemplateTableServiceImpl extends ServiceImpl<TemplateTableDao, TemplateTableEntity> implements TemplateTableService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TemplateTableEntity> page = this.page(
                new Query<TemplateTableEntity>().getPage(params),
                new QueryWrapper<TemplateTableEntity>()
        );

        return new PageUtils(page);
    }

}