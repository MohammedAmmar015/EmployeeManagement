/**
 * <p>
 * Employee class - This is POJO or Model class For Role
 * </p>
 * @author Mohammed Ammar
 * @version 1.0 
 * @since 12/08/2022
 *
 **/
package com.ideas2it.employee.dto;


public class RoleDto {
    private int roleId;

    private String description;  

    public RoleDto() {
	
    }

    /**
    * <p> 
    * This is Constructor for Role class
    * </p>
    * @param description - role like, Trainee or Trainer 
    **/
    public RoleDto(String description) {
        this.description = description;
    }

    public int getRoleId() {
	return roleId;
    }

    public void setRoleId(int roleId) {
	this.roleId = roleId;
    }  

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    } 

    @Override
    public String toString() {
	return description;
    }
}