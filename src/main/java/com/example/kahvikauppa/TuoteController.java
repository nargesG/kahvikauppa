
package com.example.kahvikauppa;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

  @GetMapping("/tuotteet")
  public String list(Model model) {
    model.addAttribute("tuotteet", tuoteRepository.findAll());
    model.addAttribute("valmistajat", valmistajaRepository.findAll());
    model.addAttribute("toimittajat", toimittajaRepository.findAll());
    model.addAttribute("osastot", osastoRepository.findAll());

    return "tuotteet";
  }

  @PostMapping("/tuotteet")
  public String create(
      @RequestParam String nimi,
      @RequestParam String kuvaus,
      @RequestParam BigDecimal hinta,
      @RequestParam Osasto osastoID,
      @RequestParam Valmistaja valmistajaID,
      @RequestParam Toimittaja toimittajaID,
      @RequestParam("tuotekuva") MultipartFile tuotekuva) throws IOException {
    Tuote tuote = new Tuote();
    tuote.setNimi(nimi);
    tuote.setKuvaus(kuvaus);
    tuote.setHinta(hinta);
    tuote.setOsasto(osastoID);
    tuote.setValmistaja(valmistajaID);
    tuote.setToimittaja(toimittajaID);

    // https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial
    String fileName = StringUtils.cleanPath(tuotekuva.getOriginalFilename());
    Path uploadPath = Paths.get("images");
    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }
    try (InputStream inputStream = tuotekuva.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      throw new IOException("Could not save image file: " + fileName, ioe);
    }

    tuote.setKuva(tuotekuva.getOriginalFilename());
    tuoteRepository.save(tuote);

    return "redirect:/tuotteet";
  }

  @GetMapping("/tuote/{id}")
  public String tuote(@PathVariable("id") Long id, Model model) {
    model.addAttribute("valmistajat", valmistajaRepository.findAll());
    model.addAttribute("toimittajat", toimittajaRepository.findAll());
    model.addAttribute("osastot", osastoRepository.findAll());

    Tuote tuote = tuoteRepository.findById(id).orElse(null);
    model.addAttribute("tuote", tuote);

    return "tuote";
  }
}