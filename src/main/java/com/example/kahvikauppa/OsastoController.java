package com.example.kahvikauppa;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/osasto/{id}")
  public String osasto(@PathVariable("id") Long id, Model model) {

    Osasto osasto = osastoRepository.getReferenceById(id);
    model.addAttribute("osasto", osasto);

    return "osasto";
  }

  @PostMapping("/osasto/{id}")
  public String update(
      @PathVariable("id") Long id,
      @RequestParam String nimi,
      @RequestParam Long osastoID)
      throws IOException {
    Osasto osasto = osastoRepository.getReferenceById(id);
    osasto.setNimi(nimi);
    osasto.setOsastoID(osastoID);

    osastoRepository.save(osasto);
    return "redirect:/osasto/" + id;
  }

}
