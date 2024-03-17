package dev.jinkela.demo.jinkelademo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaMenu;
import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.services.JinkelaMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/jinkela/s")
@RequiredArgsConstructor
public class JinkelaCommonApiController {
  private final JinkelaMenuService jinkelaMenuService;

  @GetMapping("/userinfo")
  public JinkelaUser loadCurrentJinkelaUser(@AuthenticationPrincipal JinkelaUser currentUser) {
    return currentUser;
  }

  @GetMapping("/menus")
  public List<JinkelaMenu> listAllMenusByJinkelaUserId(@AuthenticationPrincipal JinkelaUser currentUser) {
    return jinkelaMenuService.listAllMenusByJinkelaUserId(currentUser.getId());
  }

}
