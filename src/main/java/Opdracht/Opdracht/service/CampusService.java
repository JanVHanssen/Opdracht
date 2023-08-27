package Opdracht.Opdracht.service;

import Opdracht.Opdracht.entity.Campus;
import Opdracht.Opdracht.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CampusService {

    Campus createCampus(Campus campus);

    Campus getCampusById(Long campusId);

    List<Campus> getAllCampus();

    Campus updateCampus(Campus campus);

    void deleteCampus(Long campusId);
}
