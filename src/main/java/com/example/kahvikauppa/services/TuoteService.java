package com.example.kahvikauppa.services;

import com.example.kahvikauppa.Osasto;
import com.example.kahvikauppa.Toimittaja;
import com.example.kahvikauppa.Tuote;
import com.example.kahvikauppa.TuoteRepository;
import com.example.kahvikauppa.Valmistaja;

import java.math.BigDecimal;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TuoteService {

  @Autowired
  private TuoteRepository tuoteRepository;

  public List<Tuote> list() {
    return tuoteRepository.findAll();
  }

  public int count() {
    return (int) tuoteRepository.count();
  }

  public int pageCount() {
    return (int) count() / 18 + 1;
  }

  public Page<Tuote> getPage(int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber - 1, 18);
    return tuoteRepository.findAll(pageable);
  }

  public Tuote get(Long id) {
    return tuoteRepository.getReferenceById(id);
  }

  @Transactional
  public void create(
      String nimi,
      String kuvaus,
      BigDecimal hinta,
      Osasto osastoID,
      Valmistaja valmistajaID,
      Toimittaja toimittajaID,
      MultipartFile kuva) throws IOException {

    Tuote tuote = new Tuote();
    tuote.setNimi(nimi);
    tuote.setKuvaus(kuvaus);
    tuote.setHinta(hinta);
    tuote.setOsasto(osastoID);
    tuote.setValmistaja(valmistajaID);
    tuote.setToimittaja(toimittajaID);

    // https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial
    String fileName = StringUtils.cleanPath(kuva.getOriginalFilename());
    if (fileName != null & fileName != "") {
      Path uploadPath = Paths.get("images");
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }
      try (InputStream inputStream = kuva.getInputStream()) {
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        tuote.setKuva(kuva.getOriginalFilename());
      } catch (IOException ioe) {
        throw new IOException("Could not save image file: " + fileName, ioe);
      }
    }
    tuoteRepository.save(tuote);

  }

  @Transactional
  public void update(
      Long id,
      String nimi,
      String kuvaus,
      BigDecimal hinta,
      Osasto osastoID,
      Valmistaja valmistajaID,
      Toimittaja toimittajaID,
      MultipartFile kuva) throws IOException {
    Tuote tuote = tuoteRepository.getReferenceById(id);
    tuote.setNimi(nimi);
    tuote.setKuvaus(kuvaus);
    tuote.setHinta(hinta);
    tuote.setOsasto(osastoID);
    tuote.setValmistaja(valmistajaID);
    tuote.setToimittaja(toimittajaID);

    String fileName = StringUtils.cleanPath(kuva.getOriginalFilename());
    if (fileName != null & fileName != "") {
      Path uploadPath = Paths.get("images");
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }
      try (InputStream inputStream = kuva.getInputStream()) {
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        tuote.setKuva(kuva.getOriginalFilename());
      } catch (IOException ioe) {
        throw new IOException("Could not save image file: " + fileName, ioe);
      }
    }
  }
}
