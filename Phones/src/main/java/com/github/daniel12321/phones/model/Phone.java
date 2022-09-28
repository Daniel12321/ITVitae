package com.github.daniel12321.phones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
public class Phone {

    @Id private int id;
    private String brand;
    private String model;
    @Column(length = 512) private String desc;
    private BigDecimal price;

    public Phone() {
    }

    public Phone(int id, String brand, String model, String desc, BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.desc = desc;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal priceWithoutTax() {
        // Math.round(price / 1.21 * 100) / 100d;
        return this.price.divide(new BigDecimal("1.21"), RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Phone) obj;
        return this.id == that.id &&
                Objects.equals(this.brand, that.brand) &&
                Objects.equals(this.model, that.model) &&
                Objects.equals(this.desc, that.desc) &&
                Objects.equals(this.price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, desc, price);
    }

    @Override
    public String toString() {
        return "Phone[" +
                "id=" + id + ", " +
                "brand=" + brand + ", " +
                "model=" + model + ", " +
                "desc=" + desc + ", " +
                "price=" + price + ']';
    }
}
