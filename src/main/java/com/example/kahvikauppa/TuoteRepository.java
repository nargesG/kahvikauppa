package com.example.kahvikauppa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuoteRepository extends JpaRepository<Tuote, Long> {
  List<Tuote> findByOsasto(Osasto osasto);

  Page<Tuote> findByOsastoIn(List<Osasto> osastot, Pageable pageable);
}
