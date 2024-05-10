package com.example.kahvikauppa;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kahvikauppa.services.ValmistajaService;

import jakarta.transaction.Transactional;

@Controller
public class ValmistajaController {

  @Autowired
  private ValmistajaRepository valmistajaRepository;

  @Autowired
  private ValmistajaService valmistajaService;

  @GetMapping("/valmistajat")
  public String list(Model model) {
    model.addAttribute("valmistajat", valmistajaService.list());

    return "valmistajat";
  }

  @Transactional
  @PostMapping("/valmistajat")
  public String create(@RequestParam String nimi, @RequestParam String url) {
    valmistajaService.create(nimi, url);

    return "redirect:/valmistajat";
  }

  @GetMapping("/valmistaja/{id}")
  public String valmistaja(@PathVariable("id") Long id, Model model) {
    model.addAttribute("valmistaja", valmistajaService.get(id));

    return "valmistaja";
  }

  @PostMapping("/valmistaja/{id}")
  public String update(
      @PathVariable("id") Long id,
      @RequestParam String nimi,
      @RequestParam String url)
      throws IOException {
    valmistajaService.update(id, nimi, url);

    return "redirect:/valmistaja/" + id;
  }

  @GetMapping("/poista-valmistaja/{id}")
  public String poistaVahvistus(@PathVariable("id") Long id, Model model) {
    model.addAttribute("valmistaja", valmistajaService.get(id));

    return "poista-valmistaja";
  }

  @PostMapping("/poista-valmistaja/{id}")
  public String delete(@PathVariable("id") Long id) throws IOException {
    valmistajaRepository.delete(valmistajaService.get(id));

    return "redirect:/valmistajat";
  }

}
