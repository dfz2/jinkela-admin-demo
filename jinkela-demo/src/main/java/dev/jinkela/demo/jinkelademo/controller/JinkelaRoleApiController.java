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

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaRole;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleCreateDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleDeleteDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleListPageDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleModifyDTO;
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
  public Page<JinkelaRole> listAllRoles(JinkelaRoleListPageDTO jinkelaRoleListPageDTO, Pageable pageable) {
    return jinkelaRoleService.listAllRoles(jinkelaRoleListPageDTO, pageable);
  }


  @GetMapping("{jinkelaRoleId}")
  public JinkelaRole getJinkelaRoleById(@PathVariable("jinkelaRoleId") @NotNull Long jinkelaRoleId) {
    return jinkelaRoleService.getJinkelaRoleById(jinkelaRoleId);
  }

  @PostMapping
  public void addNewRoleToDb(@RequestBody @Valid JinkelaRoleCreateDTO jinkelaRoleCreateDTO) {
    jinkelaRoleService.addNewRoleToDb(jinkelaRoleCreateDTO);
  }

  @PutMapping("/{jinkelaRoleId}")
  public void mmodifyRoleToDb(@PathVariable("jinkelaRoleId") @NotNull Long jinkelaRoleId, @RequestBody @Valid JinkelaRoleModifyDTO jinkelaRoleModifyDTO) {
    jinkelaRoleService.mmodifyRoleToDb(jinkelaRoleId, jinkelaRoleModifyDTO);
  }

  @DeleteMapping("/{jinkelaRoleId}")
  public void deleteRoleFromDb(@PathVariable("jinkelaRoleId") @NotNull Long jinkelaRoleId, @RequestBody JinkelaRoleDeleteDTO jinkelaRoleDeleteDTO) {
    jinkelaRoleService.deleteRoleFromDb(jinkelaRoleId, jinkelaRoleDeleteDTO);
  }

}
