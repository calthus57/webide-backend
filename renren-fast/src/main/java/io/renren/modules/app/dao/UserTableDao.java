package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.UserTableEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author ybf
 * @email ${email}
 * @date 2021-09-30 23:57:36
 */
@Mapper
public interface UserTableDao extends BaseMapper<UserTableEntity> {
	
}
