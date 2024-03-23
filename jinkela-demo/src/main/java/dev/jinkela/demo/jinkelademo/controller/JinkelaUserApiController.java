package dev.jinkela.demo.jinkelademo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserCreateDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserListPageDTO;
import dev.jinkela.demo.jinkelademo.services.JinkelaUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/jinkela/users")
public class JinkelaUserApiController {
  private final JinkelaUserService jinkelaUserService;

  @GetMapping
  public Page<JinkelaUser> listAllJikelaUsers(JinkelaUserListPageDTO request, Pageable pageable) {
    return jinkelaUserService.listAllJikelaUsers(request, pageable);
  }

  @GetMapping("/{jinkelaUserId}")
  public JinkelaUser loadByJinkelaUserId(@PathVariable Long jinkelaUserId) {
    return jinkelaUserService.loadByJinkelaUserId(jinkelaUserId);
  }


  @PostMapping
  public void addNewUserToDb(@RequestBody @Valid JinkelaUserCreateDTO jinkelaUserCreateDTO) {
    jinkelaUserService.addNewUserToDb(jinkelaUserCreateDTO);
  }

  
  @PutMapping("/{jinkelaUserId}")
  public void modifyUserToDb(@PathVariable("jinkelaUserId") Long jinkelaUserId, @RequestBody @Valid JinkelaUserCreateDTO jinkelaUserCreateDTO) {
    jinkelaUserService.modifyUserToDb(jinkelaUserId, jinkelaUserCreateDTO);
  }

}
