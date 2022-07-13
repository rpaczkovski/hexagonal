package com.meli.hexagonal.domain;

import com.meli.hexagonal.domain.valueObject.CustomId;
import com.meli.hexagonal.domain.valueObject.Money;
import com.meli.hexagonal.domain.valueObject.Promotion;
import com.meli.hexagonal.domain.valueObject.Tax;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

enum Category {
    FOOD, CLOTHS, DECORATION, ELECTRONICS
}

public class Product {
    private CustomId id;
    private String name;
    private String description;
    private Money price;
    private Category category;
    private List<Tax> taxList;
    private Promotion promotion;

    public Product(CustomId id, Money price, String name) {
        validatePrice(price);
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Product(Money price, String name) {
        validatePrice(price);
        this.id = CustomId.randomId();
        this.price = price;
        this.name = name;
    }

    public Product(Money price, String name, String description, Category category, List<Tax> taxLis) {
        validatePrice(price);
        this.id = CustomId.randomId();
        this.price = price;
        this.name = name;
        this.category = category;
        this.taxList = taxLis;
        addDescription(description);
    }

    public Long getId() {
        return id.getId();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        BigDecimal totalWithTax = price.getValue().add(getTotalTax());
        BigDecimal totalPromotion = getTotalPromotion(totalWithTax);
        return totalWithTax.subtract(totalPromotion);
    }

    public void addDescription(String description) {
        validateDescription(description);
        this.description = getFormattedDescription(description);
    }

    private void validatePrice(Money money) {
        if (money.getValue().compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("The price cannot be less than zero.");
        }
    }

    private void validateDescription(String description) {
        if (description.isEmpty() || description.isBlank() || description.length() < 30) {
            throw new IllegalArgumentException("The description should have at least 30 characters.");
        }
    }

    private String getFormattedDescription(String description) {
        return StringUtils.capitalize(description);
    }

    private BigDecimal getTotalTax() {
        return taxList.stream()
            .map(
                tax -> this.price.getValue().divide(new BigDecimal(100)).multiply(tax.getPercent()))
            .reduce(BigDecimal.ZERO, (subtotal, element) -> subtotal.add(element));
    }

    private BigDecimal getTotalPromotion(BigDecimal total) {
        return ObjectUtils.isEmpty(promotion) ?
            BigDecimal.ZERO :
            total.subtract(total.divide(new BigDecimal(100)).multiply(promotion.getPercent()));
    }

}
