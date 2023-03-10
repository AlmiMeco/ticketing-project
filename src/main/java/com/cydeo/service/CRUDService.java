package com.cydeo.service;

import java.util.List;

public interface CRUDService <T,ID> { // -> placeholders (generic) types <T,ID>
                                      // -> UserService Implementation   <UserDTO, String>
                                      // -> RoleService Implementation   <RoleDTO, Long>


        T save(T t);

        T findById(ID id);

        List<T> findAll();

        void deleteById(ID id);

}
