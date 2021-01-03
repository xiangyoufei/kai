package com.kai.api.views;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ViewController {
    @GetMapping(value = {"/","/index"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }
    @GetMapping("/issue")
    public ModelAndView issue() {
        return new ModelAndView("issue");
    }
    @GetMapping("/atr")
    public ModelAndView atr() {
        return new ModelAndView("atr");
    }
    @GetMapping("/fashion")
    public ModelAndView fashion() {
        return new ModelAndView("fashion");
    }
    @GetMapping("/beautiy")
    public ModelAndView beautiy() {
        return new ModelAndView("beautiy");
    }
    @GetMapping("/photography")
    public ModelAndView photography() {
        return new ModelAndView("photography");
    }
    @GetMapping("/video")
    public ModelAndView video() {
        return new ModelAndView("video");
    }
    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }
    @GetMapping("/submisstion")
    public ModelAndView submisstion() {
        return new ModelAndView("submisstion");
    }
    @GetMapping("/news")
    public ModelAndView news() {
        return new ModelAndView("news");
    }
    @GetMapping("/music")
    public ModelAndView music() {
        return new ModelAndView("music");
    }
}
