package Opdracht.Opdracht.repository;

import Opdracht.Opdracht.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository  extends JpaRepository<Campus, Long> {
}
