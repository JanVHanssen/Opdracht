package Opdracht.Opdracht.service.impl;

import Opdracht.Opdracht.entity.Reservatie;
import Opdracht.Opdracht.repository.ReservatieRepository;
import Opdracht.Opdracht.service.ReservatieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservatieServiceImpl implements ReservatieService {

    private ReservatieRepository reservatieRepository;

    @Override
    public Reservatie createReservatie(Reservatie reservatie) {
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
        existingReservatie.setStart(reservatie.getStart());
        existingReservatie.setEinde(reservatie.getEinde());
        existingReservatie.setComment(reservatie.getComment());
        existingReservatie.setMax_aantal(reservatie.getMax_aantal());
        Reservatie updatedReservatie = reservatieRepository.save(existingReservatie);
        return updatedReservatie;
    }

    @Override
    public void deleteReservatie(Long reservatieId) {
        reservatieRepository.deleteById(reservatieId);
    }


}
