package com.pml.cloud.controller;

import com.pml.cloud.dao.po.User;
import com.pml.cloud.response.ResponseCode;
import com.pml.cloud.response.ResponseData;
import com.pml.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：libin
 * @Package：com.pml.cloud.controller
 * @Project：usersystem
 * @name：UserController
 * @Date：2023/3/20 17:40
 * @Filename：UserController
 */
@RestController
@CrossOrigin
public class UserController {


    private final UserService userServiceImpl;

    public UserController(@Qualifier("userServiceImpl") UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    /**
     * 新增对象
     * @param user user
     * @return
     */
    @PostMapping(path = "/user")
    public ResponseData<String> create(@RequestBody User user){

        userServiceImpl.insert(user);
        return ResponseData.success(ResponseCode.RC100.getMessage());
    }

    /**
     * 修改对象
     * @param id id
     * @param user user
     */
    @PutMapping(value = "/user/{id}")
    public ResponseData<String> update(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        userServiceImpl.update(user);
        return ResponseData.success();
    }

    /**
     * 根据id删除对象
     * @param id id
     */
    @DeleteMapping(value = "/user/{id}")
    public ResponseData<String>  delete(@PathVariable Long id){
        userServiceImpl.delete(id);
        return ResponseData.success();
    }

    @GetMapping("/user/{id}")
    public ResponseData<User> getUser(@PathVariable Long id){

        User user = userServiceImpl.getUser(id);

        return ResponseData.success(user);

    }

    @GetMapping("/user")
    public ResponseData<?> getUserAll(){

        List<User> userlist = userServiceImpl.getUserAll();

        return ResponseData.success(userlist);

    }

    @GetMapping("/user/list")
    public ResponseData<?> pageUserInfo(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return ResponseData.success(userServiceImpl.getUserList(pageNum, pageSize));
    }

}
