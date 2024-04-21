package dev.jinkela.demo.jinkelademo.services.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.datas.repositories.JinkelaMenuRepository;
import dev.jinkela.demo.jinkelademo.datas.repositories.JinkelaUserRepository;
import dev.jinkela.demo.jinkelademo.exceptions.UserNotFoundException;
import dev.jinkela.demo.jinkelademo.services.JinkelaUserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class JinkelaUserServiceImpl implements JinkelaUserService {
  private final JinkelaUserRepository jinkelaUserRepository;
  private final JinkelaMenuRepository jinkelaMenuRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    JinkelaUser jinkelaUser = jinkelaUserRepository.findJinkelaUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("用户名密码错误"));
    if (jinkelaUser.getId().equals(1000000L)) {
      jinkelaUser.setAuthorities(
          jinkelaMenuRepository.findAll()
              .stream()
              .filter(it -> StringUtils.hasLength(it.getPermission()))
              .map(it -> new SimpleGrantedAuthority(it.getPermission()))
              .collect(Collectors.toSet()));
    } else {
      jinkelaUser.setAuthorities(
          jinkelaUserRepository.listAllPermissionsByJinkelaUserId(jinkelaUser.getId())
              .stream()
              .map(it -> new SimpleGrantedAuthority(it))
              .collect(Collectors.toSet()));
    }
    return jinkelaUser;
  }

  @Override
  public JinkelaUser loadJinkelaUserByUsername(String username) {
    return jinkelaUserRepository.findJinkelaUserByUsername(username).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public JinkelaUser loadByJinkelaUserId(Long jinkelaUserId) {
    return jinkelaUserRepository.findById(jinkelaUserId).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public Page<JinkelaUser> listAllJikelaUsers(String name, Pageable pageable) {
    JinkelaUser probe = new JinkelaUser();
    probe.setDeleted(false);
    probe.setUsername(name);
    probe.setNickname(name);
    final var matching = ExampleMatcher.matching().withIgnoreNullValues()
        .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
        .withMatcher("nickname", ExampleMatcher.GenericPropertyMatchers.contains());
    Example<JinkelaUser> example = Example.of(probe, matching);
    return jinkelaUserRepository.findAll(example, pageable);
  }

  @Transactional
  @Override
  public void addNewUserToDb(JinkelaUser jinkelaUser) {
    var newjinkelaUser = new JinkelaUser(
        jinkelaUser.getNickname(),
        jinkelaUser.getUsername(),
        passwordEncoder.encode("123456"));
    jinkelaUserRepository.saveOrUpdate2(newjinkelaUser);
  }

  @Transactional
  @Override
  public void modifyUserToDb(JinkelaUser jinkelaUser) {
    var dbjinkelaUser = jinkelaUserRepository.findById(jinkelaUser.getId()).orElseThrow(UserNotFoundException::new);
    jinkelaUser.setNickname(jinkelaUser.getNickname());
    jinkelaUserRepository.saveOrUpdate2(dbjinkelaUser);
  }

  @Transactional
  @Override
  public void deleteJinkelaUser(Long jinkelaUserId) {
    var jinkelaUser = jinkelaUserRepository.findById(jinkelaUserId).orElseThrow(UserNotFoundException::new);
    jinkelaUser.setEnabled(false);
    jinkelaUser.setDeleted(true);
    jinkelaUserRepository.save(jinkelaUser);
  }

}
