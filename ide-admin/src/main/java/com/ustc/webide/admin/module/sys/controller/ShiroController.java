package com.ustc.webide.admin.module.sys.controller;

import com.ustc.webide.admin.module.sys.entity.SysUserEntity;
import com.ustc.webide.admin.module.sys.entity.SysUserTokenEntity;
import com.ustc.webide.admin.module.sys.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("shiro")
public class ShiroController extends AbstractController{
    @Autowired
    private ShiroService shiroService;

    @GetMapping("/userPermissions/{userId}")
    public Set<String> getUserPermissions(@PathVariable("userId") long userId) {
        return shiroService.getUserPermissions(userId);
    }

    @GetMapping("/userToken/{token}")
    public SysUserTokenEntity queryByToken(@PathVariable("token")String token) {
        return shiroService.queryByToken(token);
    }

    @GetMapping("user/{userId}")
    public SysUserEntity queryUser(@PathVariable("userId") Long userId) {
        return shiroService.queryUser(userId);
    }
}
