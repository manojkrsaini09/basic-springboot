package com.metacube.sageclarity.predictable.vo;

import com.metacube.sageclarity.predictable.entity.Role;
import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.enums.UserRoleEnum;

import java.util.ArrayList;
import java.util.List;

public class UserVO {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private List<RoleVO> roles;
    public UserVO(){}
    public UserVO(User user){
        this.id = user.getId();
        this.firstName = user.getName();
        this.lastName = user.getLastName();
        this.userName = user.getUsername();
        if(user.getRoles() != null && user.getRoles().size() > 0){
            this.roles = new ArrayList<>();
            for(Role role:user.getRoles()){
                this.roles.add(new RoleVO(role));
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<RoleVO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVO> roles) {
        this.roles = roles;
    }
}