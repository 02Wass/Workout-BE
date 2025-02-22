package com.workout.controller.SiteNavigation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
  public class About {
    @GetMapping("/About")
    public String index() {
      return "Page for About section";
    }
  }
