package dev.jinkela.demo.jinkelademo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import jakarta.validation.Valid;

public interface JinkelaUserService extends UserDetailsService{
  JinkelaUser loadJinkelaUserByUsername(String username);

  Page<JinkelaUser> listAllJikelaUsers(String name, Pageable pageable);

  void addNewUserToDb(@Valid JinkelaUser jinkelaUser);

  JinkelaUser loadByJinkelaUserId(Long jinkelaUserId);

  void modifyUserToDb(JinkelaUser jinkelaUser);

  void deleteJinkelaUser(Long jinkelaUserId);

}
