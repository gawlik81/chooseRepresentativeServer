package pl.com.altar.dao.impl;

import pl.com.altar.dao.MittingsDAO;
import pl.com.altar.domain.Mitting;
import pl.com.altar.domain.RepresentantCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class MittingDAOImpl implements MittingsDAO {

  @Autowired
  private JdbcTemplate jdbc;

  @Override public List<Mitting> findAllMittings() throws Exception{
    String sql = "select * from umowione_spotkania";
    List<Mitting> mittings = jdbc.query(sql, (rs, i) -> Mitting.builder()
        .id(rs.getInt("id"))
        .code(rs.getString("kod"))
        .representant(rs.getString("przedstawiciel"))
        .region(rs.getString("region"))
        .email(rs.getString("email"))
        .dateMitting(rs.getDate("data_spotkania").toLocalDate())
        .dateAppointment(rs.getDate("data_umowienia").toLocalDate())
        .build()
    );
    return mittings;
  }

  @Override
  public List<Mitting> findMitingsByDateMiting(LocalDate dateMitting) throws Exception{

    String sql = "select * from umowione_spotkania where data_spotkania = ?";

    List<Mitting> mittings = jdbc.query(sql, new Object[] { Date.valueOf(dateMitting) }, (rs, i) -> Mitting.builder()
        .id(rs.getInt("id"))
        .code(rs.getString("kod"))
        .representant(rs.getString("przedstawiciel"))
        .region(rs.getString("region"))
        .email(rs.getString("email"))
        .dateMitting(rs.getDate("data_spotkania").toLocalDate())
        .dateAppointment(rs.getDate("data_umowienia").toLocalDate())
        .build()
    );
    return mittings;
  }

  @Override
  public List<RepresentantCount> getCountMittingsByDate(LocalDate dateMitting) throws Exception{

    String sql = "select przedstawiciel,count(*) ilosc from umowione_spotkania where data_spotkania = ? group by przedstawiciel order by ilosc";

    List<RepresentantCount> representantCounts = jdbc.query(sql, new Object[] { Date.valueOf(dateMitting) }, (rs, i) -> RepresentantCount.builder()
        .representant(rs.getString("przedstawiciel"))
        .count(rs.getInt("ilosc"))
        .build()
    );

    return representantCounts;
  }

  @Override public Integer addMitting(Mitting mitting) throws Exception{

    String sql = "insert into umowione_spotkania (kod, region, przedstawiciel, email, data_spotkania, data_umowienia) values (?, ?, ?, ?, ?, ?)";

    Integer ret = jdbc.update(sql, new Object[] { mitting.getCode(), mitting.getRegion(), mitting.getRepresentant(), mitting.getEmail(), Date.valueOf(mitting.getDateMitting()),
        Date.valueOf(mitting.getDateAppointment()) });

    return ret;
  }
}
