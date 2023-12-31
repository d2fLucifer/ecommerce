package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.RoleService;
import com.lucifer.ecommerce.dto.RoleDto;
import com.lucifer.ecommerce.dto.UserDto;
import com.lucifer.ecommerce.exception.ResourceAlreadyExitException;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Role;
import com.lucifer.ecommerce.model.User;
import com.lucifer.ecommerce.repository.RoleRepository;
import com.lucifer.ecommerce.repository.UserRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final GenericMapper genericMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RoleDto createRole(RoleDto roleDto) {
        Optional<Role> exitRole = roleRepository.findByRole(roleDto.getRole());
        if (exitRole.isPresent())
            throw new ResourceAlreadyExitException("Role", "name", roleDto.getRole());
        Role role = genericMapper.map(roleDto, Role.class);
        return genericMapper.map(roleRepository.save(role), RoleDto.class);
    }

    @Transactional
    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        role.setRole(roleDto.getRole());


        RoleDto map = genericMapper.map(roleRepository.save(role), RoleDto.class);
        map.getUserDtos().addAll(genericMapper.mapList(role.getUsers(), UserDto.class));
        return map;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = genericMapper.mapList(roles, RoleDto.class);


        roleDtos.forEach(roleDto -> {
            List<User> source = userRepository.findByRole(genericMapper.map(roleDto, Role.class)).get();
            List<UserDto> userDtos = genericMapper.mapList(source, UserDto.class);
            roleDto.setUserDtos(userDtos);
        });

        return roleDtos;
    }


    @Transactional
    @Override
    public void deleteRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

        List<User> users = userRepository.findByRole(role).orElse(null);
        assert users != null;
        users.forEach(user -> user.setRole(null));

        userRepository.saveAll(users);
        roleRepository.delete(role);
    }


    @Override
    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

        return genericMapper.map(role, RoleDto.class);

    }
}
