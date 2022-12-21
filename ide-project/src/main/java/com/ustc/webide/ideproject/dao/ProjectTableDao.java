package com.ustc.webide.ideproject.dao;

import com.ustc.webide.ideproject.entity.ProjectTableEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author ybf
 * @email ${email}
 * @date 2021-10-09 12:33:56
 */
@Mapper
public interface ProjectTableDao extends BaseMapper<ProjectTableEntity> {
	ProjectTableEntity findRecentEiditProjectByUserId(@Param("userId") Integer userId);
	List<ProjectTableEntity> findAllProjectByUserIdDescLastEiditTime(@Param("userId") Integer userId);
	List<ProjectTableEntity> findRecentTemplateByUid(@Param("userId") Integer userId);
}
