package com.nitheesh.user_service.controller;

import com.nitheesh.user_service.dto.UserRequestDto;
import com.nitheesh.user_service.dto.UserResponseDto;
import com.nitheesh.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getALlUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto,@PathVariable String id){
        return userService.updateUser(userRequestDto,id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String  id){
        userService.deleteById(id);
    }
}
