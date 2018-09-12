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
    private Long companyId;
    private Boolean enabled;
    private String email;
    private CompanyVO companyVO;
    public UserVO(){}
    public UserVO(User user){
        this.id = user.getId();
        this.firstName = user.getName();
        this.lastName = user.getLastName();
        this.userName = user.getUsername();
        this.enabled = user.isEnabled();
        this.email = user.getEmailId();
        if(user.getRoles() != null && user.getRoles().size() > 0){
            this.roles = new ArrayList<>();
            for(Role role:user.getRoles()){
                this.roles.add(new RoleVO(role));
            }
        }
        if(user.getCompany() != null){
            this.companyVO = new CompanyVO(user.getCompany());
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CompanyVO getCompanyVO() {
        return companyVO;
    }

    public void setCompanyVO(CompanyVO companyVO) {
        this.companyVO = companyVO;
    }
}
