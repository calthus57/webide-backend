package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author ybf
 * @email ${email}
 * @date 2021-09-30 23:57:36
 */
@Data
@TableName("ide_project_table")
public class ProjectTableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	@TableId
	private Integer projectId;
	/**
	 * 
	 */
	private Integer projectType;
	/**
	 * 
	 */
	private String projectStruct;
	/**
	 * 
	 */
	private Date createTime;

}
