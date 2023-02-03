package com.jap.eCommerce.Jap_ECom.model.domain.cart;

import com.jap.eCommerce.Jap_ECom.model.domain.catalog.Product;
import com.jap.eCommerce.Jap_ECom.model.domain.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "cart_entry")
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "CART_ENTRY_ID")
    private Long cartEntryID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "QTY")
    private Integer qty;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    public Long getCartEntryID() {
        return cartEntryID;
    }

    public void setCartEntryID(Long cartEntryID) {
        this.cartEntryID = cartEntryID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
