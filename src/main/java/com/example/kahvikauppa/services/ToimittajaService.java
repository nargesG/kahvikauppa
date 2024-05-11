package com.example.kahvikauppa.services;

import com.example.kahvikauppa.Toimittaja;
import com.example.kahvikauppa.ToimittajaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ToimittajaService {

  @Autowired
  private ToimittajaRepository toimittajaRepository;

  public List<Toimittaja> list() {
    return toimittajaRepository.findAll();
  }

  public Toimittaja get(Long id) {
    return toimittajaRepository.getReferenceById(id);
  }

  @Transactional
  public void create(String nimi, String yhteyshenkilo, String yhteyshenkillonEmail) {
    Toimittaja toimittaja = new Toimittaja();
    toimittaja.setNimi(nimi);
    toimittaja.setYhteyshenkilo(yhteyshenkilo);
    toimittaja.setYhteyshenkillonEmail(yhteyshenkillonEmail);

    toimittajaRepository.save(toimittaja);
  }

  @Transactional
  public void update(Long id, String nimi, String yhteyshenkilo, String yhteyshenkillonEmail) {
    Toimittaja toimittaja = new Toimittaja();
    toimittaja.setNimi(nimi);
    toimittaja.setYhteyshenkilo(yhteyshenkilo);
    toimittaja.setYhteyshenkillonEmail(yhteyshenkillonEmail);
  }

}
