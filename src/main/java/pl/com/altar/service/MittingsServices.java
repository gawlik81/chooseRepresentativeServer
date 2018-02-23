package pl.com.altar.service;

import pl.com.altar.dao.MittingsDAO;
import pl.com.altar.domain.Mitting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MittingsServices {

  @Autowired
  MittingsDAO mittingsDAO;

  public List<Mitting> getAllMittings() throws Exception {
    return mittingsDAO.findAllMittings();
  }

  public List<Mitting> getMitingsByDateMiting(LocalDate dateMitting) throws Exception {
    return mittingsDAO.findMitingsByDateMiting(dateMitting);
  }

}
