package altar.com.pl.service;

import altar.com.pl.dao.MittingsDAO;
import altar.com.pl.domain.Mitting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MittingsServices {

  @Autowired
  MittingsDAO mittingsDAO;

  public List<Mitting> getAllMittings() {
    return mittingsDAO.findAllMittings();
  }

}
