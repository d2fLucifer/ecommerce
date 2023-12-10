package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.Response.UserResponse;
import com.lucifer.ecommerce.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    void deleteUserById( String id);
    UserDto updateUserById(String id, UserDto userDto);
    UserResponse findAllUsers();
    UserResponse findUserById(String id);


}
