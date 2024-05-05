package com.example.kahvikauppa;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Tuote {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String nimi;
  private String kuvaus;
  private String kuva;
  private BigDecimal hinta;

  @ManyToOne
  private Osasto osasto;

  @ManyToOne
  private Toimittaja toimittaja;

  @ManyToOne
  private Valmistaja valmistaja;

}
