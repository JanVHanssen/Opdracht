package Opdracht.Opdracht.service;

import Opdracht.Opdracht.entity.Reservatie;
import Opdracht.Opdracht.repository.ReservatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReservatieService {

    Reservatie createReservatie(Reservatie reservatie);

    Reservatie getReservatieById(Long reservatieId);

    List<Reservatie> getAllReservaties();

    Reservatie updateReservaties(Reservatie reservatie);

    void deleteReservatie(Long reservatieId);
}
