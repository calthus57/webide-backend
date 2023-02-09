/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.ustc.webide.common.feign.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    private List<Long> roleIdList;

    private Long createUserId;

    private Date createTime;

}
