package pl.com.altar.service;

import pl.com.altar.dao.MittingsDAO;
import pl.com.altar.domain.Mitting;
import pl.com.altar.domain.Representant;
import pl.com.altar.domain.RepresentantCount;
import lombok.extern.log4j.Log4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j
public class RepresentantServices {
  List<Representant> representants = new ArrayList<Representant>();

  @Value("${reprezentant.path}")
  @NotEmpty
  private String filePath;

  @Autowired
  MittingsDAO mittingsDAO;

  public Representant getRepresentantByCodeAndDate(LocalDate dateMetting, String code) throws Exception {
    Integer index = 0, ii = 0, count = Integer.MAX_VALUE;
    Boolean zero = true;

    List<RepresentantCount> representantCounts = mittingsDAO.getCountMittingsByDate(dateMetting);

    List<Representant> representantsByCode = representants.stream()
        .filter(representant -> code.equals(representant.getCode()))
        .collect(Collectors.toList());

    if (representantsByCode == null || representantsByCode.size() == 0) {
      return null;
    }

    if (representantCounts.size() > 0) {
      for (Representant t : representantsByCode) {
        zero = true;
        for (RepresentantCount r : representantCounts) {
          if (t.getRepresentant().equals(r.getRepresentant())) {
            zero = false;
            if (r.getCount() < count) {
              index = ii;
              count = r.getCount();
              break;
            }
            else {
              break;
            }
          }
        }
        if (zero) {
          count = 0;
          index = ii;
        }

        if (count == 0)
          break;
        ii++;

      }
    }
    else {
      index = 0;
    }

    mittingsDAO.addMitting(Mitting.builder()
        .code(code)
        .region(representantsByCode.get(index).getRegion())
        .representant(representantsByCode.get(index).getRepresentant())
        .email(representantsByCode.get(index).getEmail())
        .dateMitting(dateMetting)
        .dateAppointment(LocalDate.now())
        .build());

    return representantsByCode.get(index);

  }

  public Boolean updateReprezentants() throws Exception {

    //InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "ISO-8859-2");

    FileReader fileReader = new FileReader(filePath);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    representants.clear();

    String textLine = null;
    do {

      textLine = bufferedReader.readLine();
      if (textLine == null)
        break;
      String[] kol = textLine.split(";");

      if (kol.length % 2 != 0) {
        log.info("Niepoprawny wiersz: " + textLine);
        continue;
      }

      try {
        for (Integer ii = 2; ii < kol.length; ii += 2) {
          if (kol[ii].length() == 0)
            continue;
          representants.add(Representant.builder()
              .code(kol[0])
              .region(kol[1])
              .representant(kol[ii])
              .email(kol[ii + 1])
              .build());
        }
      }
      catch (ArrayIndexOutOfBoundsException e) {
        log.info("Niepoprawny wiersz: " + textLine);
      }
    }
    while (true);

    bufferedReader.close();
    return true;
  }

  public List<Representant> getAllReprezentant() {
    return representants;
  }

}
