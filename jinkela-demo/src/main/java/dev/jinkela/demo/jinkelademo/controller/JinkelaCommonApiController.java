package dev.jinkela.demo.jinkelademo.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaMenu;
import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import dev.jinkela.demo.jinkelademo.services.JinkelaMenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jinkela/s")
@RequiredArgsConstructor
public class JinkelaCommonApiController {
  private final JinkelaMenuService jinkelaMenuService;

  @GetMapping("/userinfo")
  public JinkelaUser loadCurrentJinkelaUser(@AuthenticationPrincipal JinkelaUser currentUser) {
    return currentUser;
  }

  @GetMapping("/{jinkelaUserId}/menus")
  public List<JinkelaMenu> listAllMenusByJinkelaUserId(@PathVariable("jinkelaUserId") String jinkelaUserId,
      @AuthenticationPrincipal JinkelaUser currentUser) {
    return jinkelaMenuService.listAllMenusByJinkelaUserId(currentUser.getId());
  }

  @GetMapping("/common/download")
  public void download(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/octet-stream");
    response.addHeader("Content-Disposition",
        String.format("attachment;filename=%s.txt", UUID.randomUUID().toString()));
    try (OutputStream output = response.getOutputStream()) {
      StreamUtils.copy("1233".getBytes(), output);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
