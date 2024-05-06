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

public class Osasto {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String nimi;
  private Long osastoID;

  @OneToMany(mappedBy = "osasto")
  private List<Tuote> tuotteet = new ArrayList<>();

}
