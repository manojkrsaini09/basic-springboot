package com.metacube.sageclarity.predictable.entity;

import com.metacube.sageclarity.predictable.enums.UserRoleEnum;
import com.metacube.sageclarity.predictable.vo.RoleVO;
import com.metacube.sageclarity.predictable.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity(name = "user")
public class User extends BaseEntity<String> implements Serializable{

	@Column
	private String name;

	@Column
	private String lastName;

	@Column(name="username",unique = true, nullable = false)
	private String username;

	@Column
	private String password;

	@Column
	private boolean enabled;

	@Column
    private String emailId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org_id", referencedColumnName = "id")
	private Company company;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<>();

	public User() {}

	public User(Long id) {
		super.setId(id);
	}

	public User(String name, String userName, String password, boolean enabled) {
		super();
		this.name = name;
		this.username = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public User(UserVO userVO){
		PasswordEncoder enc = new BCryptPasswordEncoder();
		if(userVO.getId()!=null){
			super.setId(userVO.getId());
		}else{
			this.password = enc.encode("user");
		}
		this.name = userVO.getFirstName();
		this.lastName = userVO.getLastName();
		this.username = userVO.getUserName();
		this.enabled = userVO.getEnabled();
		this.emailId = userVO.getEmail();
		if(userVO.getRoles()!=null){
			for(RoleVO role:userVO.getRoles()){
				this.roles.add(new Role(role));
			}

		}
		if(userVO.getCompanyVO() != null){
			this.setCompany(new Company(userVO.getCompanyVO()));
		}
	}

	public User(UserVO userVO,User user){
		BeanUtils.copyProperties(user,this);
		if(userVO.getId()!=null){
			super.setId(userVO.getId());
		}
		this.name = userVO.getFirstName();
		this.lastName = userVO.getLastName();
		this.username = userVO.getUserName();
		this.enabled = userVO.getEnabled();
		this.emailId = userVO.getEmail();
		if(userVO.getRoles()!=null){
			this.roles.clear();
			for(RoleVO role:userVO.getRoles()){
				this.roles.add(new Role(role));
			}

		}
		if(userVO.getCompanyVO() != null){
			this.setCompany(new Company(userVO.getCompanyVO()));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
