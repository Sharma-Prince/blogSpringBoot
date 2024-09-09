package com.example.blog.controller;


import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    private UserService userService;

    // POST - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createUserDto = userService.createUser(userDto);
        return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    // PUT - update user
    @PostMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
        UserDto updatedUser = userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",false),HttpStatus.OK);
    }

    // GET - user get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}
