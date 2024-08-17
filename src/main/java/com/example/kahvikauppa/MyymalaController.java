package com.example.kahvikauppa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kahvikauppa.services.OsastoService;
import com.example.kahvikauppa.services.TuoteService;

@Controller
public class MyymalaController {

  @Autowired
  private TuoteService tuoteService;

  @Autowired
  private OsastoService osastoService;

  @GetMapping("/myymala")
  public String showSivu(@RequestParam(required = true, defaultValue = "1") int page, Model model) {
    Page<Tuote> tuotteet = tuoteService.getPage(page);
    int pageCount = tuoteService.pageCount();
    model.addAttribute("tuotteet", tuotteet);
    model.addAttribute("pageCount", pageCount);
    model.addAttribute("currentPage", page);

    return "myymala";
  }

  @GetMapping("/myymala/{osastoNimi}")
  public String showOsastonTuoteet(
      @PathVariable("osastoNimi") String osastoNimi,
      @RequestParam(required = true, defaultValue = "1") int page,
      Model model) {
    Osasto osasto = osastoService.getByName(osastoNimi);
    List<Osasto> osastot1 = osastoService.getByParent(osasto);

    // We make a list to find the leaf level osastot
    osastot1.add(osasto);
    List<Osasto> osastot2 = osastoService.getByParents(osastot1);

    // Now we add the level 0 osasto to cover all them
    osastot2.add(osasto);

    Page<Tuote> tuotteet = tuoteService.getPaginatedProductsByOsasto(osastot2, page, 18);

    model.addAttribute("tuotteet", tuotteet);
    model.addAttribute("osasto", osasto);
    model.addAttribute("pageCount", tuotteet.getTotalPages());
    model.addAttribute("currentPage", page);

    return "myymala";
  }
}
