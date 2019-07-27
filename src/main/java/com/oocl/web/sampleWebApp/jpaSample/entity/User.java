package com.oocl.web.sampleWebApp.jpaSample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
