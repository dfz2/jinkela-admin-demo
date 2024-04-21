package dev.jinkela.demo.jinkelademo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaRole;
import dev.jinkela.demo.jinkelademo.services.JinkelaRoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/jinkela/roles")
@RequiredArgsConstructor
public class JinkelaRoleApiController {

  private final JinkelaRoleService jinkelaRoleService;


  
  @GetMapping
  @PreAuthorize("hasAuthority('jueseguanli2')")
  public Page<JinkelaRole> listAllRoles(String name, Pageable pageable) {
    return jinkelaRoleService.listAllRolesByName(name, pageable);
  }

  @GetMapping("{jinkelaRoleId}")
  @PreAuthorize("hasAuthority('juesexiangxi')")
  public JinkelaRole getJinkelaRoleById(@PathVariable("jinkelaRoleId") @NotNull Long jinkelaRoleId) {
    return jinkelaRoleService.getJinkelaRoleById(jinkelaRoleId);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('juesexinzeng')")
  public void addNewRoleToDb(@RequestBody @Valid JinkelaRole jinkelaRole) {
    jinkelaRoleService.addNewRoleToDb(jinkelaRole);
  }

  @PutMapping("/{jinkelaRoleId}")
  @PreAuthorize("hasAuthority('juesegengxin')")
  public void mmodifyRoleToDb(@RequestBody @Valid JinkelaRole jinkelaRole) {
    jinkelaRoleService.mmodifyRoleToDb(jinkelaRole);
  }

  @DeleteMapping("/{jinkelaRoleId}")
  @PreAuthorize("hasAuthority('jueseshanchu')")
  public void deleteRoleFromDb(@PathVariable("jinkelaRoleId") @NotNull Long jinkelaRoleId) {
    jinkelaRoleService.deleteRoleFromDb(jinkelaRoleId);
  }

}
