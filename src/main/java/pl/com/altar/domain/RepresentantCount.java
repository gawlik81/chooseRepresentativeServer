package pl.com.altar.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RepresentantCount {
  String representant;
  Integer count;
}
