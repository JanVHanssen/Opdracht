package Opdracht.Opdracht.repository;

import Opdracht.Opdracht.entity.Lokaal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface LokaalRepository extends JpaRepository<Lokaal, Long> {
    @Query("SELECT l FROM Lokaal l WHERE l NOT IN " +
            "(SELECT r.lokaal FROM Reservatie r " +
            " WHERE r.start <= :startDate AND r.einde >= :startDate)")
    List<Lokaal> findAvailableLokalen(LocalDateTime startDate);

    @Query("SELECT l FROM Lokaal l WHERE l NOT IN " +
            "(SELECT r.lokaal FROM Reservatie r " +
            " WHERE r.start <= :endDate AND r.einde >= :endDate)")
    List<Lokaal> findAvailableLokalenUntilEndDate(LocalDateTime endDate);

    List<Lokaal> findByCapaciteitGreaterThanEqual(int minimumCapacity);
}
