package io.renren.modules.sys.controller;

import io.renren.common.utils.Constant;
import io.renren.modules.sys.entity.SysMenuEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserTokenEntity;
import io.renren.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
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
