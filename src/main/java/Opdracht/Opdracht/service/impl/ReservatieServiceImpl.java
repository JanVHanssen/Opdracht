package Opdracht.Opdracht.service.impl;

import Opdracht.Opdracht.entity.Reservatie;
import Opdracht.Opdracht.repository.ReservatieRepository;
import Opdracht.Opdracht.service.ReservatieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservatieServiceImpl implements ReservatieService {

    private ReservatieRepository reservatieRepository;

    @Override
    public Reservatie createReservatie(Reservatie reservatie) {
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
        List<Reservatie> conflictingReservations = (List<Reservatie>) reservatieRepository.findByLokaalAndDates(
                existingReservatie.getLokaal(),
                reservatie.getStart(),
                reservatie.getEinde()
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
