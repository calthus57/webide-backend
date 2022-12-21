package com.ustc.webide.ideproject.entity;

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
 * @date 2021-10-14 10:24:32
 */
@Data
@TableName("template_file_table")
public class TemplateFileTableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer pid;
	/**
	 * 
	 */
	private Integer templateId;
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

}
