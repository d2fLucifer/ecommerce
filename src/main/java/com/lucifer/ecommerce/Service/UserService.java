package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    void deleteUserById( String id);
    UserDto updateUserById(String id, UserDto userDto);
    UserDto findAllUsers();
    UserDto findUserById(String id);


}
