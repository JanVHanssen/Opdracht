package Opdracht.Opdracht.service.impl;

import Opdracht.Opdracht.entity.Campus;
import Opdracht.Opdracht.entity.Lokaal;
import Opdracht.Opdracht.entity.Reservatie;
import Opdracht.Opdracht.entity.User;
import Opdracht.Opdracht.repository.CampusRepository;
import Opdracht.Opdracht.repository.ReservatieRepository;
import Opdracht.Opdracht.repository.UserRepository;
import Opdracht.Opdracht.service.ReservatieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservatieServiceImpl implements ReservatieService {
    @Autowired
    private ReservatieRepository reservatieRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reservatie createReservatie(Reservatie reservatie) {
        User user = userRepository.findById(reservatie.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        reservatie.setUser(user);

        LocalDateTime currentDateTime = LocalDateTime.now();

        if (reservatie.getStart().isBefore(currentDateTime)) {
            throw new IllegalArgumentException("New start date is in the past");
        }

        if (reservatie.getEinde().isBefore(currentDateTime)) {
            throw new IllegalArgumentException("New end date is in the past");
        }
        if (reservatie.getStart().isAfter(reservatie.getEinde())) {
            throw new IllegalArgumentException("Start date is after the end date");
        }
        return reservatieRepository.save(reservatie);
    }

    @Override
    public Reservatie getReservatieById(Long reservatieId) {
        Optional<Reservatie> optionalReservatie = reservatieRepository.findById(reservatieId);
        return optionalReservatie.get();
    }

    @Override
    public List<Reservatie> getAllReservaties() {
        return reservatieRepository.findAll();
    }

    @Override
    public Reservatie updateReservaties(Reservatie reservatie) {
        Reservatie existingReservatie = reservatieRepository.findById(reservatie.getId()).get();

        LocalDateTime currentDateTime = LocalDateTime.now();

        if (reservatie.getStart().isBefore(currentDateTime)) {
            throw new IllegalArgumentException("New start date is in the past");
        }

        if (reservatie.getEinde().isBefore(currentDateTime)) {
            throw new IllegalArgumentException("New end date is in the past");
        }
        if (reservatie.getStart().isAfter(reservatie.getEinde())) {
            throw new IllegalArgumentException("Start date is after the end date");
        }
        List<Reservatie> conflictingReservations = reservatieRepository.findByLokaalAndStartBetweenAndEindeBetween(
                existingReservatie.getLokaal(),
                reservatie.getStart(),
                reservatie.getEinde(),
                existingReservatie.getStart(),
                existingReservatie.getEinde()
        );

        if (!conflictingReservations.isEmpty()) {
            throw new IllegalArgumentException("The 'Lokaal' is already reserved for this time period");
        }
        existingReservatie.setStart(reservatie.getStart());
        existingReservatie.setEinde(reservatie.getEinde());
        existingReservatie.setComment(reservatie.getComment());
        existingReservatie.setMax_aantal(reservatie.getMax_aantal());
        existingReservatie.setLokaal(reservatie.getLokaal());
        Reservatie updatedReservatie = reservatieRepository.save(existingReservatie);
        return updatedReservatie;
    }

    @Override
    public void deleteReservatie(Long reservatieId) {
        reservatieRepository.deleteById(reservatieId);
    }


}
