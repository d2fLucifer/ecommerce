package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto register(UserDto userDto);
    void deleteUserById( Long id);
    UserDto updateUserById(Long id, UserDto userDto);
    List<UserDto> findAllUsers();
    UserDto findUserById(Long id);


}
