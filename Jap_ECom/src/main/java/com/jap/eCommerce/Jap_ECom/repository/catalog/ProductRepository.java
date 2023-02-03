package com.jap.eCommerce.Jap_ECom.repository.catalog;

import com.jap.eCommerce.Jap_ECom.model.domain.catalog.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
