package pl.com.altar.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Mitting {
  Integer id;
  String code;
  String region;
  String representant;
  String email;
  LocalDate dateMitting;
  LocalDate dateAppointment;
}
