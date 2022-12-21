package com.ustc.webide.ideproject.service;

import com.ustc.webide.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.webide.ideproject.entity.TemplateFileTableEntity;

import java.util.Map;

/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-10-14 10:24:32
 */
public interface TemplateFileTableService extends IService<TemplateFileTableEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

