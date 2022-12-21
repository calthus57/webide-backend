package com.ustc.webide.ideproject.dao;

import com.ustc.webide.ideproject.entity.FileTableEntity;
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
public interface FileTableDao extends BaseMapper<FileTableEntity> {
	List<FileTableEntity> getFileTableListByProjectId(@Param("id") Integer id);

    void deleteProjectFileList(@Param("projectId") Integer projectId);
}
