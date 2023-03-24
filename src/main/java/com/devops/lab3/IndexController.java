package com.devops.lab3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
public class IndexController {

    @GetMapping("/")
    public String greeting(Model model){
        LocalTime currentTime = LocalTime.now();
        model.addAttribute("greeting", "Welcome to COMP367");
        return "index";
    }

}
