package com.jap.eCommerce.Jap_ECom.model.dto.cart;

import com.jap.eCommerce.Jap_ECom.model.domain.cart.CartEntry;

public class CartEntryDTO {

    private Long cartEntryID;

    private Long cartID;

    private Long productID;

    private Integer qty;

    private Double totalPrice;

    public CartEntryDTO() {
    }

    public CartEntryDTO(CartEntry cartEntry, Long cartID) {
        this.cartEntryID = cartEntry.getCartEntryID();
        this.cartID = cartID;

        if (cartEntry.getProduct() != null) {
            this.productID = cartEntry.getProduct().getProductID();
        }

        this.qty = cartEntry.getQty();
        this.totalPrice = cartEntry.getTotalPrice();
    }

    public Long getCartEntryID() {
        return cartEntryID;
    }

    public void setCartEntryID(Long cartEntryID) {
        this.cartEntryID = cartEntryID;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
