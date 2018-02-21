package altar.com.pl.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Mitting {
  Integer id;
  String code;
  String representant;
  LocalDateTime dataTime;
}
