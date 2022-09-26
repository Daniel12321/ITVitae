package com.github.daniel12321.springtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/str")
public class StringController {

    @GetMapping(path = "/reverse")
    public String reverse(@RequestParam(value = "txt", defaultValue = "test") String txt) {
        String str = "";

        for (char c : txt.toCharArray()) {
            str = c + str;
        }

        return str;
    }

    @GetMapping(path = "/palindrome")
    public String isPalindrome(@RequestParam(value = "txt", defaultValue = "test") String txt) {
        return "The word " + txt + " is " + (this.reverse(txt).equalsIgnoreCase(txt) ? "" : "not ") + "a palindrome!";
    }
}
