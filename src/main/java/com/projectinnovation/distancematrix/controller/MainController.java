package com.projectinnovation.distancematrix.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    @Value("${point.start.lat}")
    private Double startLat;

    @Value("${point.start.lng}")
    private Double startLng;

    @Value("${point.end.lat}")
    private Double endLat;

    @Value("${point.end.lng}")
    private Double endLng;

    @GetMapping("/t")
    public String simpleHtml(Model model){
        return "map";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("startLat", startLat);
        model.addAttribute("startLng", startLng);
        model.addAttribute("endLat", endLat);
        model.addAttribute("endLng", endLng);

        return "index";
    }

    @GetMapping("/plot")
    public String plot(Model model){
        return "plot";
    }
}
