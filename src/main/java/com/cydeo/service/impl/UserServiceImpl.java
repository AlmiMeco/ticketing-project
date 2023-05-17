package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }


    @Override
    public List<UserDTO> listAllUsers() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream().map(userMapper::convertToDto)
                .collect(Collectors.toList());


    }

    @Override
    public UserDTO findByUserName(String userName) {

        return userMapper.convertToDto(userRepository.findByUserName(userName));

    }

    @Override
    public void save(UserDTO user) {

//        userRepository.save(userMapper.convertToEntity(user));

        user.setEnabled(true);

        User obj = userMapper.convertToEntity(user);

        obj.setPassWord(encoder.encode(obj.getPassWord())); //encoding our password (plain-text )

        userRepository.save(obj);

    }

    @Override
    public void deleteByUserName(String userName) {

        User user = userRepository.findByUserName(userName);

        userRepository.delete(user);

        user.setIsDeleted(true);

//        userRepository.deleteByUserName(userName);

    }

    @Override // -> returning UserDTO (Security reasons)
    public UserDTO update(UserDTO user) {

        // Find user
        User user1 = userRepository.findByUserName(user.getUserName());

        //Map updated UserDTO -> User Entity
        User convertedUser = userMapper.convertToEntity(user);

        //Set ID of Old user to updated user
        convertedUser.setId(user1.getId());

        //Save Updated user
        userRepository.save(convertedUser);


        return findByUserName(user.getUserName());

    }

    @Override
    public void deleteFromUiNotFromDb(String userName) {

        User user = userRepository.findByUserName(userName);

        user.setIsDeleted(true);

        userRepository.save(user);

    }

    @Override
    public List<UserDTO> listAllByRole(String role) {

        List<User> users = userRepository.findByRoleDescriptionIgnoreCase(role);

        /* Stream is used bc Converter converts (ENTITY -> DTO) :: We need to convert ( List<Entity>  -> List<DTO> ) */
        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());

    }
}
