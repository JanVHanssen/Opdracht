package Opdracht.Opdracht.repository;


import Opdracht.Opdracht.entity.Reservatie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservatieRepository  extends JpaRepository<Reservatie, Long> {
}
