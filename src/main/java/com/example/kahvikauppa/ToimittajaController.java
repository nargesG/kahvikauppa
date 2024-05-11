package com.example.kahvikauppa;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kahvikauppa.services.ToimittajaService;

import jakarta.transaction.Transactional;

@Controller
public class ToimittajaController {

  @Autowired
  private ToimittajaRepository toimittajaRepository;

  @Autowired
  private ToimittajaService toimittajaService;

  @GetMapping("/toimittajat")
  public String list(Model model) {
    model.addAttribute("toimittajat", toimittajaService.list());

    return "toimittajat";
  }

  @Transactional
  @PostMapping("/toimittajat")
  public String create(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
      @RequestParam String yhteyshenkillonEmail) {
    toimittajaService.create(nimi, yhteyshenkilo, yhteyshenkillonEmail);

    return "redirect:/toimittajat";
  }

  @GetMapping("/toimittaja/{id}")
  public String toimittaja(@PathVariable("id") Long id, Model model) {
    model.addAttribute("toimittaja", toimittajaService.get(id));

    return "toimittaja";
  }

  @PostMapping("/toimittaja/{id}")
  public String update(
      @PathVariable("id") Long id,
      @RequestParam String nimi,
      @RequestParam String yhteyshenkilo,
      @RequestParam String yhteyshenkillonEmail)
      throws IOException {
    toimittajaService.update(id, nimi, yhteyshenkilo, yhteyshenkillonEmail);

    return "redirect:/toimittaja/" + id;
  }

  @GetMapping("/poista-toimittaja/{id}")
  public String poistaVahvistus(@PathVariable("id") Long id, Model model) {
    model.addAttribute("toimittaja", toimittajaService.get(id));

    return "poista-toimittaja";
  }

  @PostMapping("/poista-toimittaja/{id}")
  public String delete(@PathVariable("id") Long id) throws IOException {
    toimittajaRepository.delete(toimittajaService.get(id));

    return "redirect:/toimittajat";
  }

}
