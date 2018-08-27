package com.metacube.sageclarity.predictable.vo;

import com.metacube.sageclarity.predictable.entity.Role;

public class RoleVO {
    private Long id;
    private String role;

    public RoleVO(){}
    public RoleVO(Role role){
        if(role.getId() != null){
            this.id = role.getId();
        }
        if(role.getRole()!=null){
            this.role = role.getRole().getValue();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
