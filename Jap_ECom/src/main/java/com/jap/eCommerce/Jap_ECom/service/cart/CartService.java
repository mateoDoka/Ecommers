package com.jap.eCommerce.Jap_ECom.service.cart;

import com.jap.eCommerce.Jap_ECom.exception.AppsException;
import com.jap.eCommerce.Jap_ECom.model.domain.cart.Cart;
import com.jap.eCommerce.Jap_ECom.model.domain.cart.CartEntry;
import com.jap.eCommerce.Jap_ECom.model.domain.catalog.Product;
import com.jap.eCommerce.Jap_ECom.model.domain.user.User;
import com.jap.eCommerce.Jap_ECom.model.dto.cart.CartDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.cart.CartEntryDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.cart.RemoveFromCartRQ;
import com.jap.eCommerce.Jap_ECom.repository.cart.CartEntryRepository;
import com.jap.eCommerce.Jap_ECom.repository.cart.CartRepository;
import com.jap.eCommerce.Jap_ECom.service.catalog.CatalogService;
import com.jap.eCommerce.Jap_ECom.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CartEntryRepository cartEntryRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public CartDTO addToCart(CartDTO addCartDTO) throws AppsException {
        Cart cart = new Cart();

        User customer = this.userService.getUserEntity(addCartDTO.getCustomerID());
        cart.setUser(customer);

        cart = this.cartRepository.saveAndFlush(cart);

        List<CartEntry> cartEntryList = new ArrayList<>();

        for (CartEntryDTO cartEntryDTO : addCartDTO.getCartEntryDTOList()) {
            CartEntry cartEntry = new CartEntry();

            Product product = this.catalogService.getProductEntity(cartEntryDTO.getProductID());
            cartEntry.setProduct(product);

            cartEntry.setQty(cartEntryDTO.getQty());
            cartEntry.setTotalPrice(this.calculateTotalPrice(product.getPrice(), cartEntryDTO.getQty()));
            cartEntry.setCart(cart);

            cartEntryList.add(cartEntry);
        }

        List<CartEntry> cartEntries = this.cartEntryRepository.saveAllAndFlush(cartEntryList);

        CartDTO cartDTO = new CartDTO(cart);

        for (CartEntry cartEntry : cartEntries) {
            cartDTO.getCartEntryDTOList().add(new CartEntryDTO(cartEntry, cart.getCartID()));
        }

        return cartDTO;
    }

    private Double calculateTotalPrice(Double unitPrice, Integer qty) {
        return unitPrice * qty;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public CartDTO getCartByID(Long cartID) throws AppsException {
        if (cartID != null) {
            if (this.cartRepository.existsById(cartID)) {
                Cart cart = this.cartRepository.getReferenceById(cartID);
                return new CartDTO(cart);
            } else {
                throw new AppsException("Cart is not exists in the system");
            }
        } else {
            throw new AppsException("Cart ID is not valid");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CartDTO> getCartsByCustomerID(Long customerID) throws AppsException {
        List<Cart> carts = this.cartRepository.getCartsByUser_UserID(customerID);
        List<CartDTO> cartDTOList = new ArrayList<>();

        for (Cart cart : carts) {
            cartDTOList.add(new CartDTO(cart));
        }

        return cartDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeFromCart(RemoveFromCartRQ removeFromCartRQ) throws AppsException {
        CartEntry cartEntry = this.cartEntryRepository.getReferenceById(removeFromCartRQ.getCartEntryID());
        cartEntryRepository.delete(cartEntry);
    }
}
