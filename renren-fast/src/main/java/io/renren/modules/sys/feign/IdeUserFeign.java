package io.renren.modules.sys.feign;

import com.ustc.webide.common.utils.R;
import io.renren.modules.sys.To.UserTableEntityTo;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gulimall-project")
public interface  IdeUserFeign {

    @RequestMapping("/ideproject/usertable/save")
    R save(@RequestBody UserTableEntityTo userTableEntityTo);

}
