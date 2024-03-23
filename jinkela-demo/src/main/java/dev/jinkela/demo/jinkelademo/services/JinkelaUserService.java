package dev.jinkela.demo.jinkelademo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserCreateDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserListPageDTO;
import jakarta.validation.Valid;

public interface JinkelaUserService extends UserDetailsService{
  JinkelaUser loadJinkelaUserByUsername(String username);

  Page<JinkelaUser> listAllJikelaUsers(JinkelaUserListPageDTO request, Pageable pageable);

  void addNewUserToDb(@Valid JinkelaUserCreateDTO jinkelaUserCreateDTO);

  JinkelaUser loadByJinkelaUserId(Long jinkelaUserId);

  void modifyUserToDb(Long jinkelaUserId, @Valid JinkelaUserCreateDTO jinkelaUserCreateDTO);

}
