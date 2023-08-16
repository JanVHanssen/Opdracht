package Opdracht.Opdracht.repository;

import Opdracht.Opdracht.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}