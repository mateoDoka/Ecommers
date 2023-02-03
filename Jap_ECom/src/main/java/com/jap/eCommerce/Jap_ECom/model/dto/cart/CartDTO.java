package com.jap.eCommerce.Jap_ECom.model.dto.cart;

import com.jap.eCommerce.Jap_ECom.model.domain.cart.Cart;
import com.jap.eCommerce.Jap_ECom.model.domain.cart.CartEntry;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private Long cartID;

    private Long customerID;

    private List<CartEntryDTO> cartEntryDTOList;

    public CartDTO() {
    }

    public CartDTO(Cart cart) {
        this.cartID = cart.getCartID();
        this.customerID = cart.getUser().getUserID();

        for (CartEntry cartEntry : cart.getCartEntries()) {
            this.getCartEntryDTOList().add(new CartEntryDTO(cartEntry, cart.getCartID()));
        }
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public List<CartEntryDTO> getCartEntryDTOList() {
        if (cartEntryDTOList == null) {
            cartEntryDTOList = new ArrayList<>();
        }
        return cartEntryDTOList;
    }

    public void setCartEntryDTOList(List<CartEntryDTO> cartEntryDTOList) {
        this.cartEntryDTOList = cartEntryDTOList;
    }
}
