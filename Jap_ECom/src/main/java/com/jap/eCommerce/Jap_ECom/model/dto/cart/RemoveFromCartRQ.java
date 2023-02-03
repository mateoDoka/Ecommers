package com.jap.eCommerce.Jap_ECom.model.dto.cart;

public class RemoveFromCartRQ {

    private Long cartID;

    private Long cartEntryID;

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public Long getCartEntryID() {
        return cartEntryID;
    }

    public void setCartEntryID(Long cartEntryID) {
        this.cartEntryID = cartEntryID;
    }
}
