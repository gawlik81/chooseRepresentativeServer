package altar.com.pl.dao.config;

import altar.com.pl.dao.MittingsDAO;
import altar.com.pl.dao.impl.MittingDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MittingDAOConfiguration {

  @Bean
  MittingsDAO mittings () {return new MittingDAOImpl();}
}
