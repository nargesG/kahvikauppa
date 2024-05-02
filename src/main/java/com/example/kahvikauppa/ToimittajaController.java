package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String create(@RequestParam String nimi, @RequestParam String yhteyshenkilo, @RequestParam String yhteyshenkillonEmail) {
        Toimittaja toimittaja = new Toimittaja();
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkillonEmail(yhteyshenkillonEmail);

        toimittajaRepository.save(toimittaja);
        return "redirect:/toimittajat";
    }
  
}
