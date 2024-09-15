package com.example.blog.service.impl;

import com.example.blog.entities.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.UserRepo;
import com.example.blog.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = toUser(userDto);
        User savedUser = userRepo.save(user);
        return toUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(
                (()-> new ResourceNotFoundException("User","user_id",userId))
        );

        user.setId(userDto.getId());
        user.setName(user.getName());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepo.save(user);
        return toUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(
                (()-> new ResourceNotFoundException("User","user_id",userId))
        );
        return toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return userList.stream()
                .map(this::toUserDto).
                collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(
                (()-> new ResourceNotFoundException("User","user_id",userId))
        );
        userRepo.delete(user);
    }

    public User toUser(UserDto userDto) {
        return this.modelMapper.map(userDto,User.class);
    }

    public UserDto toUserDto(User user) {
        return this.modelMapper.map(user,UserDto.class);
    }

}
