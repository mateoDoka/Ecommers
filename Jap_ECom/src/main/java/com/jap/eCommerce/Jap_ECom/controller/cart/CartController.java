package com.jap.eCommerce.Jap_ECom.controller.cart;

import com.jap.eCommerce.Jap_ECom.constants.AppsConstants;
import com.jap.eCommerce.Jap_ECom.exception.AppsException;
import com.jap.eCommerce.Jap_ECom.model.common.ResponseDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.cart.CartDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.cart.RemoveFromCartRQ;
import com.jap.eCommerce.Jap_ECom.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(value = "/addToCart", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CartDTO>> addToCart(@RequestBody CartDTO addCartDTO) {
        ResponseDTO<CartDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        CartDTO cartDTO;
        try {
            cartDTO = this.cartService.addToCart(addCartDTO);

            response.setResult(cartDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getCartByID/{cartID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CartDTO>> getCartByID(@PathVariable Long cartID) {
        ResponseDTO<CartDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        CartDTO cartDTO;
        try {
            cartDTO = this.cartService.getCartByID(cartID);

            response.setResult(cartDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getCartsByCustomerID/{customerID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<CartDTO>>> getCartsByCustomerID(@PathVariable Long customerID) {
        ResponseDTO<List<CartDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        List<CartDTO> cartDTOList;
        try {
            cartDTOList = this.cartService.getCartsByCustomerID(customerID);

            response.setResult(cartDTOList);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(value = "/removeFromCart", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<String>> removeFromCart(@RequestBody RemoveFromCartRQ removeFromCartRQ) {
        ResponseDTO<String> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            this.cartService.removeFromCart(removeFromCartRQ);

            response.setResult("SUCCESS");
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
