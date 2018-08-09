package com.metacube.sageclarity.predictable.service.securityhelpers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.metacube.sageclarity.predictable.entity.User;

public class UserAdapter implements UserDetails{
	
	 private User user;

	  public UserAdapter(User user) {
	    this.user = user;
	  }

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	    user.getRoles().stream().forEach(authorities::add);
	    return authorities;
	  }

	  @Override
	  public String getPassword() {
	    return user.getPassword();
	  }

	  @Override
	  public String getUsername() {
	    return user.getUsername();
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

}
