package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.kahvikauppa.services.TuoteService;

@Controller
public class ItemController {

  @Autowired
  private TuoteService tuoteService;

  @GetMapping("/item/{id}")
  public String tuote(@PathVariable("id") Long id, Model model) {
    model.addAttribute("tuote", tuoteService.get(id));

    return "item";
  }
}
