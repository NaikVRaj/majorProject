package com.spe.workwize.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long userId;
  @Column(unique = true, nullable = false)
  private String username;
  @Column(nullable = false)
  @JsonIgnore
  private String password;
  @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false, unique = true)
  private String phone;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String businessTitle;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USER_ROLES",
          joinColumns = {
                  @JoinColumn(name = "USER_ID")
          },
          inverseJoinColumns = {
                  @JoinColumn(name = "ROLE_ID") })
  private Set<Role> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<SimpleGrantedAuthority> grantedRoles = new HashSet<>();
    roles.forEach(role->{
      grantedRoles.add(new SimpleGrantedAuthority(role.getName()));
    });
    return grantedRoles;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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