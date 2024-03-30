package dev.jinkela.demo.jinkelademo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaUserCreateDTO;
import dev.jinkela.demo.jinkelademo.dtos.ListAllJikelaUsersDTO;
import dev.jinkela.demo.jinkelademo.services.JinkelaUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/jinkela/users")
public class JinkelaUserApiController {
  private final JinkelaUserService jinkelaUserService;

  @GetMapping
  public Page<JinkelaUser> listAllJikelaUsers(ListAllJikelaUsersDTO request, Pageable pageable) {
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


  @DeleteMapping("/{jinkelaUserId}")
  public void deleteJinkelaUser(@PathVariable("jinkelaUserId") Long jinkelaUserId) {
    jinkelaUserService.deleteJinkelaUser(jinkelaUserId);
  }



}
