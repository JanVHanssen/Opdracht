package Opdracht.Opdracht.service.impl;

import Opdracht.Opdracht.entity.Campus;
import Opdracht.Opdracht.entity.Lokaal;
import Opdracht.Opdracht.entity.User;
import Opdracht.Opdracht.repository.CampusRepository;
import Opdracht.Opdracht.repository.LokaalRepository;
import Opdracht.Opdracht.service.LokaalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LokaalServiceImpl implements LokaalService {
    @Autowired
    private LokaalRepository lokaalRepository;

    @Autowired
    private CampusRepository campusRepository;

    @Override
    public Lokaal createLokaal(Lokaal lokaal) {
        Campus campus = campusRepository.findById(lokaal.getCampus().getId())
                .orElseThrow(() -> new EntityNotFoundException("Campus not found"));

        lokaal.setCampus(campus);

        return lokaalRepository.save(lokaal);
    }

    @Override
    public Lokaal getLokaalById(Long lokaalId) {
        Optional<Lokaal> optionalLokaal = lokaalRepository.findById(lokaalId);
        return optionalLokaal.get();
    }

    @Override
    public List<Lokaal> getAllLokalen() {
        return lokaalRepository.findAll();
    }

    @Override
    public Lokaal updateLokaal(Lokaal lokaal) {
        Lokaal existingLokaal = lokaalRepository.findById(lokaal.getId()).get();
        existingLokaal.setNaam(lokaal.getNaam());
        existingLokaal.setType(lokaal.getType());
        existingLokaal.setCapaciteit(lokaal.getCapaciteit());
        existingLokaal.setVerdieping(lokaal.getVerdieping());
        Lokaal updatedLokaal = lokaalRepository.save(existingLokaal);
        return updatedLokaal;
    }

    @Override
    public void deleteLokaal(Long lokaalId) {
        lokaalRepository.deleteById(lokaalId);
    }
    public List<Lokaal> getAvailableLokalenStartingFromDate(LocalDateTime startDate) {
        return lokaalRepository.findAvailableLokalen(startDate);
    }
    public List<Lokaal> getAvailableLokalenUntilEndDate(LocalDateTime endDate) {
        return lokaalRepository.findAvailableLokalenUntilEndDate(endDate);
    }
    public List<Lokaal> getLokalenWithMinimumCapacity(int minimumCapacity) {
        return lokaalRepository.findByCapaciteitGreaterThanEqual(minimumCapacity);
    }
}
