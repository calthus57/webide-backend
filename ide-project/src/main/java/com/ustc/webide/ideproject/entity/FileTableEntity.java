package com.ustc.webide.ideproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("ide_file_table")
public class FileTableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer projectId;
	/**
	 * 
	 */
	private Integer pid;
	/**
	 * 
	 */
	@TableId(type= IdType.AUTO)
	private Integer id;
	/**
	 * 
	 */
	private String fileType;
	/**
	 * 
	 */
	private String suffix;
	/**
	 * 
	 */
	private String fileAddr;
	/**
	 * 
	 */
	private String fileName;
	/**
	 * 
	 */
	private String filetext;
	@TableField(exist = false)
	private Integer virtulId;
}
