package com.example.kahvikauppa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data

public class Toimittaja {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nimi;
  private String yhteyshenkilo;
  private String yhteyshenkillonEmail;

  @OneToMany(mappedBy = "toimittaja")
  private List<Tuote> tuotteet = new ArrayList<>();

}
