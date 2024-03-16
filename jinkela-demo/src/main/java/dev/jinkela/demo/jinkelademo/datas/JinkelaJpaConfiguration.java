package dev.jinkela.demo.jinkelademo.datas;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareRef")
@EnableJpaRepositories(basePackageClasses = { BaseJinkelaRepository.class })
class JinkelaJpaConfiguration {

  @Bean
  AuditorAware<Long> auditorAwareRef() {
    return () -> Optional.of(1L);
  }

  @Bean
  PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
    return it -> it.setOneIndexedParameters(true);
  }
}
