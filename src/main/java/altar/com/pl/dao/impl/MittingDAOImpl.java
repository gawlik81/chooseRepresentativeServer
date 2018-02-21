package altar.com.pl.dao.impl;

import altar.com.pl.dao.MittingsDAO;
import altar.com.pl.domain.Mitting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MittingDAOImpl implements MittingsDAO {

  @Autowired
  private JdbcTemplate jdbc;

  @Override public List<Mitting> findAllMittings() {
    String sql = "select * from mittings";
    List<Mitting> mittings = jdbc.query(sql, (rs,i) -> Mitting.builder()
        .id(rs.getInt("id"))
        .code(rs.getString("code"))
        .representant(rs.getString("representant"))
        .dataTime(rs.getTimestamp("datatime").toLocalDateTime())
        .build()
    );
    return mittings;
  }
}
