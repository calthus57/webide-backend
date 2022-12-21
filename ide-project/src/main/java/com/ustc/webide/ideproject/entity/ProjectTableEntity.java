package com.ustc.webide.ideproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @date 2021-10-09 12:33:56
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
	private String projectName;
	/**
	 * 
	 */
	@TableId(type= IdType.AUTO)
	private Integer projectId;
	/**
	 * 
	 */
	private Integer templateId;
	private Integer projectType;
	private Integer projectStatu;
	/**
	 * 
	 */
	private String projectStruct;
	/**
	 * 
	 */
	private Date createTime;
	private Date lastEiditTime;

}
