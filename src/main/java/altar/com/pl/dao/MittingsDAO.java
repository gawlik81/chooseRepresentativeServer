package altar.com.pl.dao;

import altar.com.pl.domain.Mitting;

import java.util.List;

public interface MittingsDAO {
  List<Mitting> findAllMittings();
}
