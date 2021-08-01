package com.projectinnovation.distancematrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class addPointController {

    @GetMapping("/addPoint")
    public String index(Model model){
        return "addPoint";
    }
}
