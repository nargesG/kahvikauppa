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
public class ValmistajaController {

  @Autowired
  private ValmistajaRepository valmistajaRepository;

  @GetMapping("/valmistajat")
  public String list(Model model) {
    model.addAttribute("valmistajat", valmistajaRepository.findAll());

    return "valmistajat";
  }

  @PostMapping("/valmistajat")
  public String create(@RequestParam String nimi, @RequestParam String url) {
    Valmistaja valmistaja = new Valmistaja();
    valmistaja.setNimi(nimi);
    valmistaja.setUrl(url);

    valmistajaRepository.save(valmistaja);
    return "redirect:/valmistajat";
  }

  @GetMapping("/valmistaja/{id}")
  public String valmistaja(@PathVariable("id") Long id, Model model) {

    Valmistaja valmistaja = valmistajaRepository.getReferenceById(id);
    model.addAttribute("valmistaja", valmistaja);

    return "valmistaja";
  }

  @PostMapping("/valmistaja/{id}")
  public String update(
      @PathVariable("id") Long id,
      @RequestParam String nimi,
      @RequestParam String url)
      throws IOException {
    Valmistaja valmistaja = valmistajaRepository.getReferenceById(id);
    valmistaja.setNimi(nimi);
    valmistaja.setUrl(url);

    valmistajaRepository.save(valmistaja);
    return "redirect:/valmistaja/" + id;
  }

  @GetMapping("/poista-valmistaja/{id}")
  public String poistaVahvistus(@PathVariable("id") Long id, Model model) {
    Valmistaja valmistaja = valmistajaRepository.getReferenceById(id);
    model.addAttribute("valmistaja", valmistaja);

    return "poista-valmistaja";
  }

  @PostMapping("/poista-valmistaja/{id}")
  public String delete(@PathVariable("id") Long id) throws IOException {
    Valmistaja valmistaja = valmistajaRepository.getReferenceById(id);
    valmistajaRepository.delete(valmistaja);

    return "redirect:/valmistajat";
  }

}
