package com.jap.eCommerce.Jap_ECom.service.user;

import com.jap.eCommerce.Jap_ECom.exception.AppsException;
import com.jap.eCommerce.Jap_ECom.model.domain.user.User;
import com.jap.eCommerce.Jap_ECom.model.dto.user.UserDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.user.UserPasswordChangeDTO;
import com.jap.eCommerce.Jap_ECom.repository.user.UserRepository;
import com.jap.eCommerce.Jap_ECom.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);

        if (user == null) {
            LOG.error("ERROR : User not found in database : {}", username);
            throw new UsernameNotFoundException("User not found in database");
        } else {
            LOG.info("INFO : User found in database : {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getDescription()));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO registerUser(UserDTO registerUserDTO) throws AppsException {
        User user = this.userRepository.findByUserName(registerUserDTO.getUserName());

        if (user != null) {
            LOG.error("ERROR : Customer already exists : {}", user.getUserName());
            throw new AppsException("User already exits");
        } else {
            user = new User();

            user.setUserName(registerUserDTO.getUserName());
            user.setFirstName(registerUserDTO.getFirstName());
            user.setLastName(registerUserDTO.getLastName());
            user.setMobile(registerUserDTO.getMobile());
            user.setUserRole(registerUserDTO.getUserRole());
            user.setEmail(registerUserDTO.getEmail());

            user.setPassword(this.generateEncodedPassword(registerUserDTO.getPassword()));

            user = this.userRepository.saveAndFlush(user);

            return new UserDTO(user, false);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO changeUserPassword(UserPasswordChangeDTO passwordChangeDTO, Long userID) throws AppsException {
        if (!this.userRepository.existsById(userID)) {
            throw new AppsException("User can't find");
        } else {
            User user = this.userRepository.getReferenceById(userID);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String oldPassword = passwordChangeDTO.getOldPassword();
            String existingPassword = user.getPassword();

            if (passwordEncoder.matches(oldPassword, existingPassword)) {
                String encodedPassword = this.generateEncodedPassword(passwordChangeDTO.getNewPassword());

                user.setPassword(encodedPassword);
                user = this.userRepository.saveAndFlush(user);

                return new UserDTO(user, false);
            } else {
                throw new AppsException("Password mismatch");
            }
        }
    }

    public String generateEncodedPassword(String plainPassword) throws AppsException {
        return PasswordUtil.generateEncodedPassword(new BCryptPasswordEncoder(), plainPassword);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public UserDTO getUserByID(Long userID) throws AppsException {
        User user = this.userRepository.getReferenceById(userID);
        return new UserDTO(user, true);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public UserDTO resetUserPassword(Long userID) throws AppsException {
        User user = this.userRepository.getReferenceById(userID);

        if (user == null) {
            throw new AppsException("User can't find");
        } else {
            //Generate random password and send to user email
            String email = user.getEmail();
//            String resetPassword =

            user = this.userRepository.saveAndFlush(user);

            return new UserDTO(user, false);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO updateUser(UserDTO userUpdateDTO, Long userID) throws AppsException {

        if (!this.userRepository.existsById(userID)) {
            LOG.error("ERROR : User can't find : {}", userUpdateDTO.getUserName());
            throw new AppsException("User can't find");
        } else {
            User user = this.userRepository.getReferenceById(userID);

            user.setUserName(userUpdateDTO.getUserName());
            user.setFirstName(userUpdateDTO.getFirstName());
            user.setLastName(userUpdateDTO.getLastName());
            user.setMobile(userUpdateDTO.getMobile());
            user.setUserRole(userUpdateDTO.getUserRole());

            user = this.userRepository.saveAndFlush(user);

            return new UserDTO(user, true);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserDTO> getAllUsers() throws AppsException {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> users = this.userRepository.findAll();

        for (User user : users) {
            userDTOList.add(new UserDTO(user, true));
        }

        return userDTOList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User getUserEntity(Long userID) throws AppsException {
        if (userID != null) {
            if (this.userRepository.existsById(userID)) {
                User user = this.userRepository.getReferenceById(userID);
                return user;
            } else {
                throw new AppsException("User is not exists in the system");
            }
        } else {
            throw new AppsException("User ID is not valid");
        }
    }
}
