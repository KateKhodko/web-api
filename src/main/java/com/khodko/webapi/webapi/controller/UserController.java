package com.khodko.webapi.webapi.controller;

import com.khodko.webapi.webapi.dto.ChangePasswordRequest;
import com.khodko.webapi.webapi.dto.ProfileDto;
import com.khodko.webapi.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ProfileDto login() {
        return userService.getCurrentUser();
    }

    @PostMapping("/register")
    public ProfileDto registerNewUser(@RequestBody ProfileDto user) {
        return userService.registerNewUser(user);
    }

    @PostMapping
    public ProfileDto update(@RequestBody ProfileDto user) {
        return userService.create(user);
    }

    @GetMapping
    public ProfileDto getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PostMapping("/change-password")
    public ProfileDto changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        ProfileDto currentUser = userService.getCurrentUser();
        changePasswordRequest.setUserId(currentUser.getId());
        return userService.changePassword(changePasswordRequest);
    }
}
