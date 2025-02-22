package com.workout.controller.SiteNavigation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
  public class StartPage {
    @GetMapping("/")
    public String index() {
      return "Page for start with login etc etc";
    }
  }
