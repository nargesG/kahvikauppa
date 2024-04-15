package com.example.kahvikauppa;

import java.math.BigDecimal;
import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data 

public class Tuote {
  @Id
  private Long id;
  private String nimi;
  private String kuvaus;
  private BigDecimal hinta;
  private Blob tuotekuva;

  @ManyToOne
  private Osasto osasto;

  @ManyToOne
  private Toimittaja toimittaja;

  @ManyToOne
  private Valmistaja valmistaja;
  
}
