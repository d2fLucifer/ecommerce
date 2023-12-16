package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.UserService;
import com.lucifer.ecommerce.dto.UserDto;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Cart;
import com.lucifer.ecommerce.model.Role;
import com.lucifer.ecommerce.model.User;
import com.lucifer.ecommerce.repository.CartRepository;
import com.lucifer.ecommerce.repository.RoleRepository;
import com.lucifer.ecommerce.repository.UserRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final GenericMapper genericMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDto register(UserDto userDto) {
        System.out.println(userDto);
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new UsernameNotFoundException("User email already exists");
        }

        User newUser = genericMapper.map(userDto, User.class);
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Cart cart = new Cart(null, newUser, null, new Date());
        newUser.setCart(cart);

        Optional<Role> userRole = roleRepository.findByRole("USER");
        if (userRole.isPresent()) {
            newUser.setRole(userRole.get());
            userRole.get().getUsers().add(newUser);
            roleRepository.save(userRole.get());
        } else {
            Role newRole = new Role();
            newRole.setRole("USER");
            roleRepository.save(newRole);
            newUser.setRole(newRole);
            newRole.getUsers().add(newUser);
            roleRepository.save(newRole);

        }

        User savedUser = userRepository.save(newUser);
        cartRepository.save(cart);

        return genericMapper.map(savedUser, UserDto.class);
    }


    @Override
    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Assuming you want to disassociate rather than delete related entities
        if (user.getOrders() != null) {
            user.getOrders().clear();
        }
        if (user.getReviews() != null) {
            user.getReviews().clear();
        }

        userRepository.delete(user);
    }


    @Override
    @Transactional
    public UserDto updateUserById(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        User savedUser = genericMapper.map(userDto, User.class);
        savedUser.setId(user.getId());
        if (userDto.getRole() != null)
            savedUser.setRole(roleRepository.findByRole(userDto.getRole()).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)));

        return genericMapper.map(userRepository.save(savedUser), UserDto.class);
    }


    @Override
    public List<UserDto> findAllUsers() {
        return genericMapper.mapList(userRepository.findAll(), UserDto.class);
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return genericMapper.map(user, UserDto.class);
    }
}
