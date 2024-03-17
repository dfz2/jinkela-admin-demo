package dev.jinkela.demo.jinkelademo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaRole;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleCreateDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleListPageDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleModifyDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface JinkelaRoleService {

  void addNewRoleToDb(JinkelaRoleCreateDTO jinkelaRoleCreateDTO);

  void mmodifyRoleToDb(Long jinkelaRoleId, @Valid JinkelaRoleModifyDTO jinkelaRoleModifyDTO);

  Page<JinkelaRole> listAllRoles(JinkelaRoleListPageDTO jinkelaRoleListPageDTO, Pageable pageable);

  JinkelaRole getJinkelaRoleById(@NotNull Long jinkelaRoleId);

}
