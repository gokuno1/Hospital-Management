package com.example.ZuulApiGateway.authtoken;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDto user; // this is my own User object
    private List<GrantedAuthority> authorities; 
    private Boolean invaliDSession=true;
    private Boolean expireSession=true;
    public MyUserDetails() {
    }
    public MyUserDetails(UserDto user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
        return this.expireSession;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.invaliDSession;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

	public Boolean getInvaliDSession() {
		return invaliDSession;
	}

	public void setInvaliDSession(Boolean invaliDSession) {
		this.invaliDSession = invaliDSession;
	}
	public Boolean getExpireSession() {
		return expireSession;
	}
	public void setExpireSession(Boolean expireSession) {
		this.expireSession = expireSession;
	}
    
 }
