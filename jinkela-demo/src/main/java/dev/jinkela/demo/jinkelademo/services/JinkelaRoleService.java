package dev.jinkela.demo.jinkelademo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaRole;
import jakarta.validation.constraints.NotNull;

public interface JinkelaRoleService {

  void addNewRoleToDb(JinkelaRole jinkelaRole);

  void mmodifyRoleToDb(JinkelaRole jinkelaRole);

  Page<JinkelaRole> listAllRolesByName(String name, Pageable pageable);

  JinkelaRole getJinkelaRoleById(@NotNull Long jinkelaRoleId);

  void deleteRoleFromDb(Long jinkelaRoleId);

}
