package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    public final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository repository, RoleMapper roleMapper) {
        this.roleRepository = repository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
        // Controller requests A list of ALL RoleDTO -> for UI dropDown box
        // Retrieve rolls from DataTable
        // Go to repo && find service (DI -> RoleRepo)
        List<Role> roleList = roleRepository.findAll();

        // Role (entity) needs to be converted to -> RoleDTO (DTO)
        // the conversion is done via Model-Mapper (external lib)

        return roleList.stream()
//               .map(i -> roleMapper.convertToDto(i)) ->  both .map() impl are the same
                 .map(roleMapper::convertToDto)
                 .collect(Collectors.toList());


    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
