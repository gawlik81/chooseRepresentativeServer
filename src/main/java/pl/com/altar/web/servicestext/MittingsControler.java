package pl.com.altar.web.servicestext;

import pl.com.altar.domain.Mitting;
import pl.com.altar.domain.Representant;
import pl.com.altar.service.MittingsServices;
import pl.com.altar.service.RepresentantServices;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Log4j
@RequestMapping("/servicesrest/mitting")
public class MittingsControler {

  @Autowired
  MittingsServices mittingsServices;

  @Autowired
  RepresentantServices representantServices;

  @GetMapping("/list")
  public List<Mitting> listMittings() throws Exception {

    return mittingsServices.getAllMittings();
  }

  @GetMapping("/list/{data}")
  public List<Mitting> listMittings3(@PathVariable String data) throws Exception {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    return mittingsServices.getMitingsByDateMiting(LocalDate.parse(data, formatter));
  }

  @GetMapping("/add/{data}/{code}")
  public String addMitting(@PathVariable String data, @PathVariable String code) throws Exception {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Representant representant = representantServices.getRepresentantByCodeAndDate(LocalDate.parse(data, formatter), code);

    if (representant == null)
      return "ERROR|Brak przedstawicieli|";

    return "OK|" + representant.getRepresentant() + "|" + representant.getEmail() + "|" + representant.getCode() + "|" + representant.getRegion() + "|";
  }



  @GetMapping("/list2")
  public List<Representant> listMittingsStr() throws IOException {
    return representantServices.getAllReprezentant();
  }

  @GetMapping("/update")
  public ResponseEntity<Boolean> updateReprezentant() throws Exception {
    representantServices.updateReprezentants();
    return new ResponseEntity<>(true, HttpStatus.OK);

  }

}
