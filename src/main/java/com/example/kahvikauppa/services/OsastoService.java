package com.example.kahvikauppa.services;

import com.example.kahvikauppa.Osasto;
import com.example.kahvikauppa.OsastoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OsastoService {

  @Autowired
  private OsastoRepository osastoRepository;

  public List<Osasto> list() {
    return osastoRepository.findAll();
  }

  public Osasto get(Long id) {
    return osastoRepository.getReferenceById(id);
  }

  @Transactional
  public void create(String nimi, Long osastoID) {
    Osasto osasto = new Osasto();
    osasto.setNimi(nimi);
    osasto.setOsastoID(osastoID);

    osastoRepository.save(osasto);
  }

  @Transactional
  public void update(Long id, String nimi, Long osastoID) {
    Osasto osasto = new Osasto();
    osasto.setNimi(nimi);
    osasto.setOsastoID(osastoID);
  }

}
