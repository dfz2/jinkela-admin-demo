package dev.jinkela.demo.jinkelademo.controller;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jinkela.demo.jinkelademo.utils.RSAUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/jinkela/open")
public class JinkelaOpenApiController {
  @GetMapping("/publickey")
  public Map<String, String> getPublicKey(HttpSession session) throws Exception {
    KeyPair keyPair = RSAUtil.getKeyPair();
    session.setAttribute("___private_key___", RSAUtil.getPrivateKeyString(keyPair));
    return new HashMap<>() {
      {
        put("publicKey", RSAUtil.getPublicKeyString(keyPair));
      }
    };
  }






  
}
