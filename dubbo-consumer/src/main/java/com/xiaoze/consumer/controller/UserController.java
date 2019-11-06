package com.xiaoze.consumer.controller;


import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoze.api.entity.User;
import com.xiaoze.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lp
 * @since 2019-10-31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = "${demo.service.version}")
    UserService userService;

    @GetMapping(value = "/list")
    public List<User> getUsers(){
        System.out.println(userService.list());
        List<User> user = userService.list();
        return user;
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        boolean flag = userService.updateById(user);
        if(flag){
            return "update success";
        }
        return "update fail";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") int id){
        boolean flag = userService.removeById(id);
        if(flag){
            return "delete success";
        }
        return "delete fail";
    }

    @GetMapping(value = "insert")
    public String Insert(){
        User user = new User();
        user.setAge(23);
        user.setName("7z");
        user.setSex("男");
        try {
            userService.save(user);
        }catch (Exception e){
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping(value = "/test")
    public List<User> Test(@RequestParam(value = "page", required = true) int page, @RequestParam(value = "pageSize", required = true) int pageSize){
        User user = new User();
        IPage<User> iPage = userService.selectAll(page, pageSize);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", iPage);
        iPage.getRecords();
        return iPage.getRecords();
    }

}
