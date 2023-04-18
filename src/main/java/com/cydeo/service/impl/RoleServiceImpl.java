package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository repository) {
        this.roleRepository = repository;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
        // Controller requests A list of ALL RoleDTO -> for UI dropDown box
        // Retrieve rolls from DataTable
        // Go to repo && find service (DI -> RoleRepo)
        List<Role> roleList = roleRepository.findAll();


        return roleList;
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
