package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    RoleDto updateRole(Long id, RoleDto roleDto);

    List<RoleDto> getAllRoles();

    void deleteRoleById(Long id);

    RoleDto getRoleById(Long id);


}
