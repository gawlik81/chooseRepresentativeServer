package pl.com.altar.dao.config;

import pl.com.altar.dao.MittingsDAO;
import pl.com.altar.dao.impl.MittingDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MittingDAOConfiguration {

  @Bean
  MittingsDAO mittings () {return new MittingDAOImpl();}
}
