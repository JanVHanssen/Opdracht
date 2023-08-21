package Opdracht.Opdracht.repository;

import java.util.List;
import Opdracht.Opdracht.entity.Lokaal;
import Opdracht.Opdracht.entity.Reservatie;
import Opdracht.Opdracht.service.ReservatieService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservatieRepository  extends JpaRepository<Reservatie, Long> {
    List<Reservatie> findByLokaalAndDates(Lokaal lokaal, LocalDateTime start, LocalDateTime end);
}
