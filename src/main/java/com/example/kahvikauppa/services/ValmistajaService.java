package com.example.kahvikauppa.services;

import com.example.kahvikauppa.Valmistaja;
import com.example.kahvikauppa.ValmistajaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ValmistajaService {

  @Autowired
  private ValmistajaRepository valmistajaRepository;

  public List<Valmistaja> list() {
    return valmistajaRepository.findAll();
  }

  public Valmistaja get(Long id) {
    return valmistajaRepository.getReferenceById(id);
  }

  @Transactional
  public void create(String nimi, String url) {
    Valmistaja valmistaja = new Valmistaja();
    valmistaja.setNimi(nimi);
    valmistaja.setUrl(url);

    valmistajaRepository.save(valmistaja);
  }

  @Transactional
  public void update(Long id, String nimi, String url) {
    Valmistaja valmistaja = valmistajaRepository.getReferenceById(id);
    valmistaja.setNimi(nimi);
    valmistaja.setUrl(url);
  }

}
