package com.jap.eCommerce.Jap_ECom.model.dto.catalog;

import com.jap.eCommerce.Jap_ECom.constants.AppsConstants;
import com.jap.eCommerce.Jap_ECom.model.domain.catalog.Product;

public class ProductDTO {

    private Long productID;

    private String code;

    private String name;

    private String description;

    private Double price;

    private AppsConstants.YesNo isEnabled;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.productID = product.getProductID();
        this.code = product.getCode();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.isEnabled = product.getIsEnabled();
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AppsConstants.YesNo getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(AppsConstants.YesNo isEnabled) {
        this.isEnabled = isEnabled;
    }
}
