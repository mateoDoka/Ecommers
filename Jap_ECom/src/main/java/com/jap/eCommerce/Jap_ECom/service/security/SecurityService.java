package com.jap.eCommerce.Jap_ECom.service.security;

import com.jap.eCommerce.Jap_ECom.repository.user.UserRepository;
import com.jap.eCommerce.Jap_ECom.model.domain.user.User;
import com.jap.eCommerce.Jap_ECom.model.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public UserDTO getUserByMobileUserName(String userName) {
        User user = this.userRepository.findByUserName(userName);
        return new UserDTO(user, false);
    }
}
