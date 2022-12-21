package com.ustc.webide.ideproject.service.impl;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ustc.webide.ideproject.dao.TemplateFileTableDao;
import com.ustc.webide.ideproject.entity.TemplateFileTableEntity;
import com.ustc.webide.ideproject.service.TemplateFileTableService;


@Service("templateFileTableService")
public class TemplateFileTableServiceImpl extends ServiceImpl<TemplateFileTableDao, TemplateFileTableEntity> implements TemplateFileTableService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TemplateFileTableEntity> page = this.page(
                new Query<TemplateFileTableEntity>().getPage(params),
                new QueryWrapper<TemplateFileTableEntity>()
        );

        return new PageUtils(page);
    }

}