package dev.jinkela.demo.jinkelademo.configuration;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Configuration
class JinkelaAppConfiguration {

  @Bean
  Validator validator() {
    ValidatorFactory validatorFactory = Validation
        .byProvider(HibernateValidator.class)
        .configure()
        .failFast(true)
        .buildValidatorFactory();
    return validatorFactory.getValidator();
  }

}
