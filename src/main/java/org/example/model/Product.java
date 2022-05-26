package org.example.model;

import org.example.enums.Category;
import org.example.enums.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private String color;
    private Category category;
    private Size size;
    private BigDecimal price;
    private BigDecimal priceUah;
    private LocalDateTime addedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceUah() {
        return priceUah;
    }

    public void setPriceUah(BigDecimal priceUah) {
        this.priceUah = priceUah;
    }

    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(LocalDateTime addedTime) {
        this.addedTime = addedTime;
    }


    public static class Builder {
        private Product newProduct;

        public Builder() {
            newProduct = new Product();
        }

        public Builder withId(int id) {
            newProduct.id = id;
            return this;
        }

        public Builder withName(String name) {
            newProduct.name = name;
            return this;
        }

        public Builder withColor(String color) {
            newProduct.color = color;
            return this;
        }

        public Builder withCategory(Category category) {
            newProduct.category = category;
            return this;
        }

        public Builder withSize(Size size) {
            newProduct.size = size;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            newProduct.price = price;
            return this;
        }

        public Builder withPriceUah(BigDecimal priceUah) {
            newProduct.priceUah = priceUah;
            return this;
        }

        public Builder withAddedTime(LocalDateTime addedTime) {
            newProduct.addedTime = addedTime;
            return this;
        }

        public Product build() {
            return newProduct;
        }

    }
}
