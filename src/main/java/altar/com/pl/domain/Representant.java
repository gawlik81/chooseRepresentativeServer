package altar.com.pl.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Representant {
  String kod;
  String region;
  String nazwa;
  String email;
}
