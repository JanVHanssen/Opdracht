package Opdracht.Opdracht.service;

import Opdracht.Opdracht.entity.Reservatie;

import java.util.List;

public interface ReservatieService {

    Reservatie createReservatie(Reservatie reservatie);

    Reservatie getReservatieById(Long reservatieId);

    List<Reservatie> getAllReservaties();

    Reservatie updateReservaties(Reservatie reservatie);

    void deleteReservatie(Long reservatieId);
}
