package dev.jinkela.demo.jinkelademo.datas.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jndi.JndiAccessor;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;

@SpringBootTest
public class JinkelaUserRepositoryTest {

  @Autowired
  private JinkelaUserRepository jinkelaUserRepository;

  @Test
  void testFindJinkelaUserByUsername() {

  }

  @Test
  void testListAllPermissionsByJinkelaUserId() {
    JinkelaUser jinkelaUser = new JinkelaUser();
    jinkelaUser.setAccountNonExpired(true);
    jinkelaUser.setCredentialsNonExpired(true);
    jinkelaUser.setAccountNonLocked(true);
    jinkelaUser.setEnabled(true);
    jinkelaUser.setUsername("admin11");

    try {
      jinkelaUserRepository.save(jinkelaUser);
    } catch (Exception e) {
      e.printStackTrace();

      System.out.println("1111111111111111111111111");
      // TODO: handle exception
    }

  }
}
