package dev.jinkela.demo.jinkelademo.datas.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "_jinkela_users")
public class JinkelaUser extends AuditMetadata implements UserDetails, CredentialsContainer {

  @Id
  private Long id;
  private String nickname;
  private String username;
  private transient String password;
  private Boolean enabled;

  @Transient
  private transient Set<GrantedAuthority> authorities = new HashSet<>();
  private Boolean accountNonExpired;
  private Boolean accountNonLocked;
  private Boolean credentialsNonExpired;


  public JinkelaUser() {
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public void eraseCredentials() {
    this.password = null;
  }

}
