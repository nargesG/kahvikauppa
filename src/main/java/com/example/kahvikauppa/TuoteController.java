
package com.example.kahvikauppa;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.kahvikauppa.services.TuoteService;
import com.example.kahvikauppa.services.ValmistajaService;

@Controller
public class TuoteController {

  @Autowired
  private TuoteRepository tuoteRepository;

  @Autowired
  private ValmistajaRepository valmistajaRepository;

  @Autowired
  private ToimittajaRepository toimittajaRepository;

  @Autowired
  private OsastoRepository osastoRepository;

  @Autowired
  private TuoteService tuoteService;

  @Autowired
  private ValmistajaService valmistajaService;

  @GetMapping("/tuotteet")
  public String list(Model model) {
    model.addAttribute("tuotteet", tuoteService.list());
    model.addAttribute("valmistajat", valmistajaRepository.findAll());
    model.addAttribute("toimittajat", toimittajaRepository.findAll());
    model.addAttribute("osastot", osastoRepository.findAll());

    return "tuotteet";
  }

  @Transactional
  @PostMapping("/tuotteet")
  public String create(
      @RequestParam String nimi,
      @RequestParam String kuvaus,
      @RequestParam BigDecimal hinta,
      @RequestParam Osasto osastoID,
      @RequestParam Valmistaja valmistajaID,
      @RequestParam Toimittaja toimittajaID,
      @RequestParam("tuotekuva") MultipartFile tuotekuva) throws IOException {

    tuoteService.create(nimi, kuvaus, hinta, osastoID, valmistajaID, toimittajaID, tuotekuva);

    return "redirect:/tuotteet";
  }

  @GetMapping("/tuote/{id}")
  public String tuote(@PathVariable("id") Long id, Model model) {
    model.addAttribute("valmistajat", valmistajaService.list());
    model.addAttribute("toimittajat", toimittajaRepository.findAll());
    model.addAttribute("osastot", osastoRepository.findAll());

    model.addAttribute("tuote", tuoteService.get(id));

    return "tuote";
  }

  @Transactional
  @PostMapping("/tuote/{id}")
  public String update(
      @PathVariable("id") Long id,
      @RequestParam String nimi,
      @RequestParam String kuvaus,
      @RequestParam BigDecimal hinta,
      @RequestParam Osasto osastoID,
      @RequestParam Valmistaja valmistajaID,
      @RequestParam Toimittaja toimittajaID,
      @RequestParam("tuotekuva") MultipartFile tuotekuva) throws IOException {
    tuoteService.update(id, nimi, kuvaus, hinta, osastoID, valmistajaID, toimittajaID, tuotekuva);

    return "redirect:/tuote/" + id;
  }

  @GetMapping("/poista-tuote/{id}")
  public String poistaVahvistus(@PathVariable("id") Long id, Model model) {
    model.addAttribute("tuote", tuoteService.get(id));

    return "poista-tuote";
  }

  @PostMapping("/poista-tuote/{id}")
  public String delete(@PathVariable("id") Long id) throws IOException {
    tuoteRepository.delete(tuoteService.get(id));

    return "redirect:/tuotteet";
  }
}