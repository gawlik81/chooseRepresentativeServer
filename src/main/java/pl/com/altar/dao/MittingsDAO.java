package pl.com.altar.dao;

import pl.com.altar.domain.Mitting;
import pl.com.altar.domain.RepresentantCount;

import java.time.LocalDate;
import java.util.List;

public interface MittingsDAO {
  List<Mitting> findAllMittings() throws Exception;

  List<Mitting> findMitingsByDateMiting(LocalDate dateMitting) throws Exception;

  List<RepresentantCount> getCountMittingsByDate(LocalDate dateMitting) throws Exception;

  Integer addMitting(Mitting mitting) throws Exception;
}
