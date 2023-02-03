package com.jap.eCommerce.Jap_ECom.model.domain.cart;

import com.jap.eCommerce.Jap_ECom.model.domain.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "CART_ID")
    private Long cartID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private User user;

    @OneToMany(mappedBy = "cart")
    private Set<CartEntry> cartEntries;

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartEntry> getCartEntries() {
        if (cartEntries == null) {
            cartEntries = new HashSet<>();
        }
        return cartEntries;
    }

    public void setCartEntries(Set<CartEntry> cartEntries) {
        this.cartEntries = cartEntries;
    }

    public void addCartEntry(CartEntry cartEntry) {
        cartEntry.setCart(this);
        this.getCartEntries().add(cartEntry);
    }

    public CartEntry getCartEntryByID(Long cartEntryID) {
        return this.getCartEntries().stream().filter
                (cartEntry -> cartEntry.getCartEntryID().equals(cartEntryID)).findFirst().orElse(null);
    }

    public void removeCartEntry(Long cartEntryID) {
        CartEntry result = this.getCartEntryByID(cartID);
        if (result != null) {
            result.setCart(null);
            this.getCartEntries().remove(result);
        }
    }
}
