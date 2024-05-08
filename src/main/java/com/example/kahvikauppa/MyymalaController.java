package com.example.kahvikauppa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyymalaController {

  @Autowired
  private TuoteRepository tuoteRepository;

  @Autowired
  private OsastoRepository osastoRepository;

  @GetMapping("/myymala")
  public String show(Model model) {
    List<Tuote> tuotteet = tuoteRepository.findAll();
    model.addAttribute("tuotteet", tuotteet);

    return "myymala";
  }

  @GetMapping("/myymala/{osastoNimi}")
  public String showOsastonTuoteet(@PathVariable("osastoNimi") String osastoNimi, Model model) {
    Osasto osasto = osastoRepository.findByNimi(osastoNimi);
    List<Osasto> osastot1 = osastoRepository.findByOsastoID(osasto.getId());

    // We make a list to find the leaf level osastot
    osastot1.add(osasto);
    List<Osasto> osastot2 = osastoRepository.findByOsastoIDIn(
        osastot1.stream().map(Osasto::getId).collect(Collectors.toList()));

    // Now we add the level 0 osasto to cover all them
    osastot2.add(osasto);

    List<Tuote> tuotteet = tuoteRepository.findByOsastoIn(osastot2);
    model.addAttribute("tuotteet", tuotteet);
    model.addAttribute("osasto", osasto);

    return "myymala";
  }
}
