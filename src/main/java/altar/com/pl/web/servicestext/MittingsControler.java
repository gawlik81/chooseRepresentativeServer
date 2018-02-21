package altar.com.pl.web.servicestext;

import altar.com.pl.domain.Mitting;
import altar.com.pl.domain.Representant;
import altar.com.pl.service.MittingsServices;
import altar.com.pl.service.RepresentantServices;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j
@RequestMapping("/mittings")
public class MittingsControler {

  @Autowired
  MittingsServices mittingsServices;

  @Autowired
  RepresentantServices representantServices;

  @GetMapping("/list")
  public List<Mitting> listMittings(){

    return mittingsServices.getAllMittings();
  }

  @GetMapping("/list2")
  public List<Representant> listMittingsStr() throws IOException {
    return representantServices.getAllReprezentant();
  }

  @GetMapping("/update")
  public Boolean updateReprezentant() throws IOException {
    return representantServices.updateReprezentants();
  }

}
