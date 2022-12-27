package com.ustc.webide.admin.module.sys.feign;

import com.ustc.webide.common.utils.R;
import com.ustc.webide.admin.module.sys.To.UserTableEntityTo;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gulimall-project")
public interface  IdeUserFeign {

    @RequestMapping("/ideproject/usertable/save")
    R save(@RequestBody UserTableEntityTo userTableEntityTo);

}
