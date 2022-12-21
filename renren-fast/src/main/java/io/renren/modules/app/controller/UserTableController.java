package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.app.entity.UserTableEntity;
import io.renren.modules.app.service.UserTableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-09-30 23:57:36
 */
@RestController
@RequestMapping("ide-project/usertable")
public class UserTableController {
    @Autowired
    private UserTableService userTableService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userTableService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")

    public R info(@PathVariable("userId") Integer userId){
		UserTableEntity userTable = userTableService.getById(userId);

        return R.ok().put("userTable", userTable);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody UserTableEntity userTable){
		userTableService.save(userTable);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody UserTableEntity userTable){
		userTableService.updateById(userTable);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Integer[] userIds){
		userTableService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
