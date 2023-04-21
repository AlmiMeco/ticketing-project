package com.cydeo.repository;

import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //get user from userName
    User findByUserName(String userName);

    List<User> findByRoleDescriptionIgnoreCase(String roleDescription);

//    @Transactional
//    User deleteByUserName(String userName);



}
