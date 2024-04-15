package com.example.kahvikauppa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data

public class Valmistaja {

  @Id
  private Long id;
  private String nimi;
  private String url;

  @OneToMany(mappedBy = "valmistaja")
  private List<Tuote> tuotet = new ArrayList<>();
  
}
