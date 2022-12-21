package com.ustc.webide.ideproject.controller;

import java.util.Arrays;
import java.util.Map;

import com.ustc.webide.common.utils.PageUtils;
import com.ustc.webide.common.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustc.webide.ideproject.entity.UserTableEntity;
import com.ustc.webide.ideproject.service.UserTableService;




/**
 * 
 *
 * @author ybf
 * @email ${email}
 * @date 2021-10-09 12:33:56
 */
@RestController
@RequestMapping("ideproject/usertable")
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
    @RequestMapping("/info")

    public R info(@RequestBody UserTableEntity userTableEntity){
		UserTableEntity userTable = userTableService.getById(userTableEntity.getUserId());

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
