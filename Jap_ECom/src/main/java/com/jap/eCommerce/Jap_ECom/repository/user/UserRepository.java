package com.jap.eCommerce.Jap_ECom.repository.user;

import com.jap.eCommerce.Jap_ECom.model.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
