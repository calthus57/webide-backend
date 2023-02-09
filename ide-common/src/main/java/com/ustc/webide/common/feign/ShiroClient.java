package com.ustc.webide.common.feign;

import com.ustc.webide.common.feign.entity.SysUserEntity;
import com.ustc.webide.common.feign.entity.SysUserTokenEntity;
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
