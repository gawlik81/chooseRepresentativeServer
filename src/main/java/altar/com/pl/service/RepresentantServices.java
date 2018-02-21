package altar.com.pl.service;

import altar.com.pl.domain.Representant;
import lombok.extern.log4j.Log4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class RepresentantServices {
  List<Representant> representants = new ArrayList<Representant>();

  @Value("${reprezentant.path}")
  @NotEmpty
  private String filePath;

  public Boolean updateReprezentants() throws IOException {

    FileReader fileReader = new FileReader(filePath);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    representants.clear();

    String textLine = null;
    do {

      textLine = bufferedReader.readLine();
      if (textLine == null)
        break;
      String[] kol = textLine.split(";");

      try {
        for (Integer ii = 2; ii < kol.length; ii += 2) {
          if (kol[ii].length() == 0)
            continue;
          representants.add(Representant.builder()
              .kod(kol[0])
              .region(kol[1])
              .nazwa(kol[ii])
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
