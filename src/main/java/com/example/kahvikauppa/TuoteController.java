
package com.example.kahvikauppa;

import java.math.BigDecimal;
import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TuoteController {
  
  @Autowired
  private TuoteRepository tuoteRepository;

  @GetMapping("/tuotet")
    public String list(Model model) {
        model.addAttribute("tuotet", tuoteRepository.findAll());

        return "tuotet";
    }

  @PostMapping("/tuotet")
    public String create(@RequestParam String nimi, @RequestParam String kuvaus, @RequestParam BigDecimal hinta, @RequestParam Blob tuoteKuva ) {
        Tuote tuote = new Tuote();
        tuote.setNimi(nimi);
        tuote.setKuvaus(kuvaus);
        tuote.setHinta(hinta);
        tuote.setTuotekuva(tuoteKuva);

        tuoteRepository.save(tuote);
        return "redirect:/tuotet";
    }
  
}