package com.example.kahvikauppa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OsastoRepository extends JpaRepository<Osasto, Long> {
  Osasto findByNimi(String nimi);

  List<Osasto> findByOsastoID(Long osastoID);

  List<Osasto> findByOsastoIDIn(List<Long> osastoID);
}
