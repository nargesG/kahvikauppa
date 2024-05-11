package com.example.kahvikauppa;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kahvikauppa.services.OsastoService;

@Controller
public class OsastoController {

  @Autowired
  private OsastoService osastoService;

  @GetMapping("/osastot")
  public String list(Model model) {
    model.addAttribute("osastot", osastoService.list());

    return "osastot";
  }

  @PostMapping("/osastot")
  public String create(@RequestParam String nimi, @RequestParam Long osastoID) {
    osastoService.create(nimi, osastoID);

    return "redirect:/osastot";
  }

  @GetMapping("/osasto/{id}")
  public String osasto(@PathVariable("id") Long id, Model model) {
    model.addAttribute("osasto", osastoService.get(id));

    return "osasto";
  }

  @PostMapping("/osasto/{id}")
  public String update(
      @PathVariable("id") Long id,
      @RequestParam String nimi,
      @RequestParam Long osastoID)
      throws IOException {
    osastoService.update(id, nimi, osastoID);

    return "redirect:/osasto/" + id;
  }

}
