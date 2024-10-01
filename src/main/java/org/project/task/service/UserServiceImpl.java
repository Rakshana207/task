package org.project.task.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.project.task.entity.Users;
import org.project.task.payload.UserDto;
import org.project.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Setter
@Service
@Getter

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users user=userDtoToEntity(userDto);
        Users savedUser=userRepository.save(user);
        return entityToUserDto(savedUser);
    }

    private Users userDtoToEntity(UserDto userDto){
        Users users=new Users();

        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        return users;
    }

    private UserDto entityToUserDto(Users savedUsers){
        UserDto userDto=new UserDto();
        userDto.setId(savedUsers.getId());
        userDto.setEmail(savedUsers.getEmail());
        userDto.setPassword(savedUsers.getPassword());
        userDto.setName(savedUsers.getName());
        return userDto;
    }
}
