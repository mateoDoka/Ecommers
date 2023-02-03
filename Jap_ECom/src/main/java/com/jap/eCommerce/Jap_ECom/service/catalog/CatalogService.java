package com.jap.eCommerce.Jap_ECom.service.catalog;

import com.jap.eCommerce.Jap_ECom.repository.catalog.ProductRepository;
import com.jap.eCommerce.Jap_ECom.repository.catalog.ProductJDBCRepository;
import com.jap.eCommerce.Jap_ECom.exception.AppsException;
import com.jap.eCommerce.Jap_ECom.model.domain.catalog.Product;
import com.jap.eCommerce.Jap_ECom.model.dto.catalog.ProductDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.catalog.ProductFilterRQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductJDBCRepository productJDBCRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO addProduct(ProductDTO saveProductDTO) throws AppsException {
        Product product = new Product();

        this.getProductByProductDTO(product, saveProductDTO);

        product = this.productRepository.saveAndFlush(product);

        return new ProductDTO(product);
    }

    private void getProductByProductDTO(Product product, ProductDTO productDTO) {
        product.setCode(productDTO.getCode());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setIsEnabled(productDTO.getIsEnabled());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO changeProduct(ProductDTO changeProductDTO, Long productID) throws AppsException {
        if (productID != null) {
            if (this.productRepository.existsById(productID)) {
                Product product = this.productRepository.getReferenceById(productID);

                this.getProductByProductDTO(product, changeProductDTO);

                product = this.productRepository.saveAndFlush(product);

                return new ProductDTO(product);
            } else {
                throw new AppsException("Product is not exists in the system");
            }
        } else {
            throw new AppsException("Product ID is not valid");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProduct(Long productID) throws AppsException {
        if (productID != null) {
            if (this.productRepository.existsById(productID)) {
                this.productRepository.deleteById(productID);
            } else {
                throw new AppsException("Product is not exists in the system");
            }
        } else {
            throw new AppsException("Product ID is not valid");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ProductDTO getProductData(Long productID) throws AppsException {
        if (productID != null) {
            if (this.productRepository.existsById(productID)) {
                Product product = this.productRepository.getReferenceById(productID);
                return new ProductDTO(product);
            } else {
                throw new AppsException("Product is not exists in the system");
            }
        } else {
            throw new AppsException("Product ID is not valid");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductDTO> searchCatalog(ProductFilterRQ filterRQ) throws AppsException {
        return this.productJDBCRepository.searchCatalog(filterRQ);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Product getProductEntity(Long productID) throws AppsException {
        if (productID != null) {
            if (this.productRepository.existsById(productID)) {
                Product product = this.productRepository.getReferenceById(productID);
                return product;
            } else {
                throw new AppsException("Product is not exists in the system");
            }
        } else {
            throw new AppsException("Product ID is not valid");
        }
    }
}
