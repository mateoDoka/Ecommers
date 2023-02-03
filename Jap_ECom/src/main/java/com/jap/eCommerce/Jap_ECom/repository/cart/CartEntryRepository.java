package com.jap.eCommerce.Jap_ECom.repository.cart;

import com.jap.eCommerce.Jap_ECom.model.domain.cart.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {
}
