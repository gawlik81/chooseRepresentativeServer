package pl.com.altar.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Representant {
  String code;
  String region;
  String representant;
  String email;
}
