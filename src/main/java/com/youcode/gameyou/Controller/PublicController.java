package com.youcode.gameyou.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @RequestMapping("/test")
    public String test() {
        return "public test";
    }
}
