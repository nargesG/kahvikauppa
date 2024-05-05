package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OsastoController {

  @Autowired
  private OsastoRepository osastoRepository;

  @GetMapping("/osastot")
  public String list(Model model) {
    model.addAttribute("osastot", osastoRepository.findAll());

    return "osastot";
  }

  @PostMapping("/osastot")
  public String create(@RequestParam String nimi, @RequestParam Long osastoID) {
    Osasto osasto = new Osasto();
    osasto.setNimi(nimi);
    osasto.setOsastoID(osastoID);

    osastoRepository.save(osasto);
    return "redirect:/osastot";
  }

}
