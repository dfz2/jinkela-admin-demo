package dev.jinkela.demo.jinkelademo.services.impl;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.datas.repositories.JinkelaUserRepository;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserCreateDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserListPageDTO;
import dev.jinkela.demo.jinkelademo.exceptions.DataConflictException;
import dev.jinkela.demo.jinkelademo.exceptions.UserNotFoundException;
import dev.jinkela.demo.jinkelademo.exceptions.UsernameConflictException;
import dev.jinkela.demo.jinkelademo.services.JinkelaUserService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class JinkelaUserServiceImpl implements JinkelaUserService {
  private final JinkelaUserRepository jinkelaUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    JinkelaUser jinkelaUser = jinkelaUserRepository.findJinkelaUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("用户名密码错误"));
    Set<GrantedAuthority> authorities = jinkelaUserRepository.listAllPermissionsByJinkelaUserId(jinkelaUser.getId())
        .stream().map(t -> new SimpleGrantedAuthority(t)).collect(Collectors.toSet());
    jinkelaUser.setAuthorities(authorities);
    return jinkelaUser;
  }

  @Override
  public JinkelaUser loadJinkelaUserByUsername(String username) {
    return jinkelaUserRepository.findJinkelaUserByUsername(username).orElseThrow(UserNotFoundException::new);
  }

  @SuppressWarnings("null")
  @Override
  public Page<JinkelaUser> listAllJikelaUsers(JinkelaUserListPageDTO request, Pageable pageable) {
    JinkelaUser probe = new JinkelaUser();
    probe.setUsername(request.getUsernmae());
    final ExampleMatcher matching = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("username",
        ExampleMatcher.GenericPropertyMatchers.contains());
    Example<JinkelaUser> example = Example.of(probe, matching);
    return jinkelaUserRepository.findAll(example, pageable);
  }

  @Transactional
  @Override
  public void addNewUserToDb(JinkelaUserCreateDTO jinkelaUserCreateDTO) {
    
    JinkelaUser jinkelaUser = new JinkelaUser();
    jinkelaUser.setEnabled(true);
    jinkelaUser.setAccountNonExpired(true);
    jinkelaUser.setAccountNonLocked(true);
    jinkelaUser.setCredentialsNonExpired(true);
    jinkelaUser.setUsername(jinkelaUserCreateDTO.getUsername());
    jinkelaUser.setNickname(jinkelaUserCreateDTO.getNickname());
    jinkelaUser.setPassword(passwordEncoder.encode("123456"));

    try {
      jinkelaUserRepository.saveOrUpdate(jinkelaUser);
    } catch (DuplicateKeyException e) {
      throw new DataConflictException("账号已存在");
    }
    

  }


}
