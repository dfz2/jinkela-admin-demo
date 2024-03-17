package dev.jinkela.demo.jinkelademo.services.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaRole;
import dev.jinkela.demo.jinkelademo.datas.repositories.JinkelaRoleRepository;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleCreateDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleListPageDTO;
import dev.jinkela.demo.jinkelademo.dtos.JinkelaRoleModifyDTO;
import dev.jinkela.demo.jinkelademo.exceptions.RoleNotFoundException;
import dev.jinkela.demo.jinkelademo.services.JinkelaRoleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
class JinkelaRoleServiceImpl implements JinkelaRoleService {
  private final JinkelaRoleRepository jinkelaRoleRepository;

  @Override
  public Page<JinkelaRole> listAllRoles(JinkelaRoleListPageDTO request, Pageable pageable) {
    JinkelaRole probe = new JinkelaRole(request.getName());
    final ExampleMatcher matching = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("name",
        ExampleMatcher.GenericPropertyMatchers.contains());
    Example<JinkelaRole> example = Example.of(probe, matching);
    return jinkelaRoleRepository.findAll(example, pageable);
  }

  @Override
  public JinkelaRole getJinkelaRoleById(Long jinkelaRoleId) {
    return jinkelaRoleRepository.findById(jinkelaRoleId).orElseThrow(RoleNotFoundException::new);
  }

  @Transactional
  @Override
  public void addNewRoleToDb(JinkelaRoleCreateDTO jinkelaRoleCreateDTO) {
    jinkelaRoleRepository.save(new JinkelaRole(jinkelaRoleCreateDTO.getName()));
  }

  @Transactional
  @Override
  public void mmodifyRoleToDb(Long jinkelaRoleId, JinkelaRoleModifyDTO jinkelaRoleModifyDTO) {
    JinkelaRole jinkelaRoleFromDb = jinkelaRoleRepository.findById(jinkelaRoleId).orElseThrow(RoleNotFoundException::new);
    jinkelaRoleFromDb.setName(jinkelaRoleModifyDTO.getName());
    jinkelaRoleFromDb.setVersion(jinkelaRoleModifyDTO.getVersion());
    jinkelaRoleRepository.save(jinkelaRoleFromDb);
  }

}
