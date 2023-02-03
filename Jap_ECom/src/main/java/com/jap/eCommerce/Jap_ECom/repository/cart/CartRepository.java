package com.jap.eCommerce.Jap_ECom.repository.cart;

import com.jap.eCommerce.Jap_ECom.model.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> getCartsByUser_UserID(Long userID);
}
