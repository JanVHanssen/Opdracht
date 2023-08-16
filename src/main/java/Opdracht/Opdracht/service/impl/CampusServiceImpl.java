package Opdracht.Opdracht.service.impl;

import Opdracht.Opdracht.entity.Campus;
import Opdracht.Opdracht.repository.CampusRepository;
import Opdracht.Opdracht.service.CampusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CampusServiceImpl implements CampusService {

    private CampusRepository campusRepository;

    @Override
    public Campus createCampus(Campus campus) {
        return campusRepository.save(campus);
    }

    @Override
    public Campus getCampusById(Long campusId) {
        Optional<Campus> optionalCampus = campusRepository.findById(campusId);
        return optionalCampus.get();
    }

    @Override
    public List<Campus> getAllCampus() {
        return campusRepository.findAll();
    }

    @Override
    public Campus updateCampus(Campus campus) {
        Campus existingCampus = campusRepository.findById(campus.getId()).get();
        existingCampus.setNaam(campus.getNaam());
        existingCampus.setAdres(campus.getAdres());
        existingCampus.setParkeerplaatsen(campus.getParkeerplaatsen());
        existingCampus.setAantal_lokalen(campus.getAantal_lokalen());
        Campus updatedCampus = campusRepository.save(existingCampus);
        return updatedCampus;
    }

    @Override
    public void deleteCampus(Long campusId) {
        campusRepository.deleteById(campusId);
    }
}
