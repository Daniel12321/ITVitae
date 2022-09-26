package com.github.daniel12321.springtest.controller;

import com.github.daniel12321.springtest.pojo.LoginRequest;
import com.github.daniel12321.springtest.pojo.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    @ResponseBody
    public LoginResponse login(@RequestBody LoginRequest login) {
        return new LoginResponse(login.username(), "FakeToken");
    }
}
