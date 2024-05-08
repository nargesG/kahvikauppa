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
public class ToimittajaController {

  @Autowired
  private ToimittajaRepository toimittajaRepository;

  @GetMapping("/toimittajat")
  public String list(Model model) {
    model.addAttribute("toimittajat", toimittajaRepository.findAll());

    return "toimittajat";
  }

  @PostMapping("/toimittajat")
  public String create(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
      @RequestParam String yhteyshenkillonEmail) {
    Toimittaja toimittaja = new Toimittaja();
    toimittaja.setNimi(nimi);
    toimittaja.setYhteyshenkilo(yhteyshenkilo);
    toimittaja.setYhteyshenkillonEmail(yhteyshenkillonEmail);

    toimittajaRepository.save(toimittaja);
    return "redirect:/toimittajat";
  }

  @GetMapping("/toimittaja/{id}")
  public String toimittaja(@PathVariable("id") Long id, Model model) {

    Toimittaja toimittaja = toimittajaRepository.getReferenceById(id);
    model.addAttribute("toimittaja", toimittaja);

    return "toimittaja";
  }

  @PostMapping("/toimittaja/{id}")
  public String update(
      @PathVariable("id") Long id,
      @RequestParam String nimi,
      @RequestParam String yhteyshenkilo,
      @RequestParam String yhteyshenkillonEmail)
      throws IOException {
    Toimittaja toimittaja = toimittajaRepository.getReferenceById(id);
    toimittaja.setNimi(nimi);
    toimittaja.setYhteyshenkilo(yhteyshenkilo);
    toimittaja.setYhteyshenkillonEmail(yhteyshenkillonEmail);

    toimittajaRepository.save(toimittaja);
    return "redirect:/toimittaja/" + id;
  }

  @GetMapping("/poista-toimittaja/{id}")
  public String poistaVahvistus(@PathVariable("id") Long id, Model model) {
    Toimittaja toimittaja = toimittajaRepository.getReferenceById(id);
    model.addAttribute("toimittaja", toimittaja);

    return "poista-toimittaja";
  }

  @PostMapping("/poista-toimittaja/{id}")
  public String delete(@PathVariable("id") Long id) throws IOException {
    Toimittaja toimittaja = toimittajaRepository.getReferenceById(id);
    toimittajaRepository.delete(toimittaja);

    return "redirect:/toimittajat";
  }

}
