package com.meli.hexagonal.domain.valueObject;

import java.math.BigDecimal;

enum TaxType {
  ICMS, ISS
}

public class Tax {

  private TaxType taxType;
  private BigDecimal percent;

  public Tax(TaxType taxType, BigDecimal percent) {
    validatePercent();
    this.taxType = taxType;
    this.percent = percent;
  }

  public BigDecimal getPercent() {
    return this.getPercent();
  }

  private void validatePercent() {
    if (percent.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("The tax percent cannot be less than zero.");
    }
  }

}
