package com.example.demo.web;

import com.example.demo.domian.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import java.util.*;

@Controller
@RequestMapping(value = "/users") //通过这里的配置实现URL在Users目录下
public class UserController {
    //创建线程安全的Map
    static Map<Long, User> map= Collections.synchronizedMap(new HashMap<Long, User>()) ;
    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = {""},method = RequestMethod.GET)
    public List<User> getUserLists(){
        List<User> userList=new ArrayList<User>(map.values());
        //处理“/users/”get请求，用来获取User列表
        //还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        return userList;
    }
    @ApiOperation(value = "创建用户",notes = "根据User对象床架用户")
    @ApiImplicitParam(name = "user",value = "用户详细实体user",required = true,dataType = "User",paramType = "path")
    @RequestMapping(value = "" ,method = RequestMethod.POST)
    public String AddUsers(@ModelAttribute User user){
        //处理“/user/”post请求，用来创建user
        //除了@ModelAttribute绑定参数，还可以通过@RequestParam从页面传递参数
        map.put(user.getId(),user);
        return "success";
    }
    @ApiOperation(value = "获取用户详细信息",notes = "根据URL的ID获取用户的详细信息")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long",paramType = "path")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return map.get(id);
    }
    @ApiOperation(value = "更新用户详细信息",notes = "根据URL的ID来指定更新对象，并根据传来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" ,value = "用户ID",required = true , dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user",value = "用户详细实体user",required = true,dataType = "User")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id,@ModelAttribute User user){
        //根据ID更新user内容
        User u=map.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        map.put(id,u);
        return "success";
    }
    @ApiOperation(value = "删除用户",notes = "根据URL的ID来指定的删除对象")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long" ,paramType = "path")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id){
        //根据ID删除用户
        map.remove(id);
        return "success";
    }
}
