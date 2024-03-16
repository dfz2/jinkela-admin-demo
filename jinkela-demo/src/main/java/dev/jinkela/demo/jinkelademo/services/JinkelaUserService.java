package dev.jinkela.demo.jinkelademo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserListPageDTO;

public interface JinkelaUserService extends UserDetailsService{
  JinkelaUser loadJinkelaUserByUsername(String username);

  Page<JinkelaUser> listAllJikelaUsers(JinkelaUserListPageDTO request, Pageable pageable);

}
