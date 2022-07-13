package com.meli.hexagonal.domain.valueObject;

import java.math.BigDecimal;

public class Promotion {

  private String name;
  private BigDecimal percent;

  public Promotion(String name, BigDecimal percent) {
    validatePercent();
    this.name = name;
    this.percent = percent;
  }

  public BigDecimal getPercent() {
    return this.getPercent();
  }

  private void validatePercent() {
    if (percent.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("The promotion percent cannot be less than zero.");
    }
  }

}
