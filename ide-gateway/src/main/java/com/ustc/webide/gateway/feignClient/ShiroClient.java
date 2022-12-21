package com.ustc.webide.gateway.feignClient;

import com.ustc.webide.gateway.entity.SysUserEntity;
import com.ustc.webide.gateway.entity.SysUserTokenEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient("ide-admin")
public interface ShiroClient {
    @GetMapping("/userPermissions/{userId}")
    public Set<String> getUserPermissions(@PathVariable("userId") long userId);

    @GetMapping("/userToken/{token}")
    public SysUserTokenEntity queryByToken(@PathVariable("token")String token);

    @GetMapping("user/{userId}")
    public SysUserEntity queryUser(@PathVariable("userId") Long userId);

}
