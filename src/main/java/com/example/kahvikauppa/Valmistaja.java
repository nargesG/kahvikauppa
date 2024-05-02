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

public class Valmistaja {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nimi;
  private String url;

  @OneToMany(mappedBy = "valmistaja")
  private List<Tuote> tuotet = new ArrayList<>();
  
}
