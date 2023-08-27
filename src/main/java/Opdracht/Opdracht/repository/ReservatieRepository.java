package Opdracht.Opdracht.repository;

import java.util.List;
import Opdracht.Opdracht.entity.Lokaal;
import Opdracht.Opdracht.entity.Reservatie;
import Opdracht.Opdracht.service.ReservatieService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface ReservatieRepository  extends JpaRepository<Reservatie, Long> {
    List<Reservatie> findByLokaalAndStartBetweenAndEindeBetween(Lokaal lokaal, LocalDateTime start, LocalDateTime end, LocalDateTime start2, LocalDateTime end2);
}
