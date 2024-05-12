package com.example.kahvikauppa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.kahvikauppa.services.OsastoService;
import com.example.kahvikauppa.services.TuoteService;

@Controller
public class MyymalaController {

  @Autowired
  private TuoteRepository tuoteRepository;

  @Autowired
  private TuoteService tuoteService;

  @Autowired
  private OsastoService osastoService;

  @GetMapping("/myymala")
  public String show(Model model) {
    List<Tuote> tuotteet = tuoteService.list();
    model.addAttribute("tuotteet", tuotteet);

    return "myymala";
  }

  @GetMapping("/myymala/{osastoNimi}")
  public String showOsastonTuoteet(@PathVariable("osastoNimi") String osastoNimi, Model model) {
    Osasto osasto = osastoService.getByName(osastoNimi);
    List<Osasto> osastot1 = osastoService.getByParent(osasto);

    // We make a list to find the leaf level osastot
    osastot1.add(osasto);
    List<Osasto> osastot2 = osastoService.getByParents(osastot1);

    // Now we add the level 0 osasto to cover all them
    osastot2.add(osasto);

    List<Tuote> tuotteet = tuoteRepository.findByOsastoIn(osastot2);
    model.addAttribute("tuotteet", tuotteet);
    model.addAttribute("osasto", osasto);

    return "myymala";
  }
}
