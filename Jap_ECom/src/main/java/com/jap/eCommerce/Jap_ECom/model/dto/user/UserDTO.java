package com.jap.eCommerce.Jap_ECom.model.dto.user;

import com.jap.eCommerce.Jap_ECom.constants.AppsConstants;
import com.jap.eCommerce.Jap_ECom.model.domain.cart.Cart;
import com.jap.eCommerce.Jap_ECom.model.domain.user.User;
import com.jap.eCommerce.Jap_ECom.model.dto.cart.CartDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long userID;

    private String userName;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String password;

    private AppsConstants.UserRole userRole;

    private List<CartDTO> cartDTOList;

    public UserDTO() {
    }

    public UserDTO(User user, boolean loadCarts) {
        this.userID = user.getUserID();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.mobile = user.getMobile();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userRole = user.getUserRole();

        if (loadCarts) {
            for (Cart cart : user.getCarts()) {
                this.getCartDTOList().add(new CartDTO(cart));
            }
        }
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppsConstants.UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(AppsConstants.UserRole userRole) {
        this.userRole = userRole;
    }

    public List<CartDTO> getCartDTOList() {
        if (cartDTOList == null) {
            cartDTOList = new ArrayList<>();
        }
        return cartDTOList;
    }

    public void setCartDTOList(List<CartDTO> cartDTOList) {
        this.cartDTOList = cartDTOList;
    }
}
