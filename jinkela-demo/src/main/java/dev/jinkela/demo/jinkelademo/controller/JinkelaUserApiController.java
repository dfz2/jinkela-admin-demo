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
  public Page<JinkelaUser> listAllJikelaUsers(String name, Pageable pageable) {
    return jinkelaUserService.listAllJikelaUsers(name, pageable);
  }

  @GetMapping("/{jinkelaUserId}")
  public JinkelaUser loadByJinkelaUserId(@PathVariable Long jinkelaUserId) {
    return jinkelaUserService.loadByJinkelaUserId(jinkelaUserId);
  }

  @PostMapping
  public void addNewUserToDb(@RequestBody @Valid JinkelaUser jinkelaUser) {
    jinkelaUserService.addNewUserToDb(jinkelaUser);
  }

  @PutMapping("/{jinkelaUserId}")
  public void modifyUserToDb(@PathVariable("jinkelaUserId") Long jinkelaUserId,
      @RequestBody @Valid JinkelaUser jinkelaUser) {
    jinkelaUserService.modifyUserToDb(jinkelaUser);
  }

  @DeleteMapping("/{jinkelaUserId}")
  public void deleteJinkelaUser(@PathVariable("jinkelaUserId") Long jinkelaUserId) {
    jinkelaUserService.deleteJinkelaUser(jinkelaUserId);
  }

}
