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
        if(currentTime.isBefore(LocalTime.NOON))
            model.addAttribute("greeting", "Good Morning, Hassaan");
        else
            model.addAttribute("greeting", "Good Afternoon, Hassaan");
        return "index";
    }

}
