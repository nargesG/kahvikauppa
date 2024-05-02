package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
  
}
