package Opdracht.Opdracht.service;

import Opdracht.Opdracht.entity.Campus;

import java.util.List;

public interface CampusService {

    Campus createCampus(Campus campus);

    Campus getCampusById(Long campusId);

    List<Campus> getAllCampus();

    Campus updateCampus(Campus campus);

    void deleteCampus(Long campusId);
}
