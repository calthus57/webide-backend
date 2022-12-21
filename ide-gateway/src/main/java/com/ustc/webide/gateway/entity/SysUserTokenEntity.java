/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.ustc.webide.gateway.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserTokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    //token
    private String token;
    //过期时间
    private Date expireTime;
    //更新时间
    private Date updateTime;

}
