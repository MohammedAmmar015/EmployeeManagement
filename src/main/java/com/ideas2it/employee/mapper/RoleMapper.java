package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.RoleDto;
import com.ideas2it.employee.models.Role;

public class RoleMapper {
    public static Role convertRoleDtoToRole(RoleDto roleDto) {
        Role role = new Role();
        role.setDescription(roleDto.getDescription());
        return role;
    }

    public static RoleDto convertRoleToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setDescription(role.getDescription());
        return roleDto;
    }
}
