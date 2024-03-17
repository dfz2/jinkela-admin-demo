package dev.jinkela.demo.jinkelademo.services.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.datas.repositories.JinkelaUserRepository;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserListPageDTO;
import dev.jinkela.demo.jinkelademo.exceptions.UserNotFoundException;
import dev.jinkela.demo.jinkelademo.services.JinkelaUserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class JinkelaUserServiceImpl implements JinkelaUserService {
  private final JinkelaUserRepository jinkelaUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return jinkelaUserRepository.findJinkelaUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("用户名密码错误"));
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

    final String[] ignorePaths = { "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled" };
    final ExampleMatcher matching = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths(ignorePaths).withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains());
    Example<JinkelaUser> example = Example.of(probe, matching);
    return jinkelaUserRepository.findAll(example, pageable);
  }

}
