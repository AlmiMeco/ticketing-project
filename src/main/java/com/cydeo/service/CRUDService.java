package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface CRUDService <T,ID> { // -> placeholders (generic) types <T,ID>
                                      // -> UserService Implementation   <UserDTO, String>
                                      // -> RoleService Implementation   <RoleDTO, Long>


        T save(T t);

        T findById(ID id);

        List<T> findAll();

        void deleteById(ID id);

        void update(T t);

}
