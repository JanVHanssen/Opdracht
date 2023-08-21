package Opdracht.Opdracht.service;

import Opdracht.Opdracht.entity.Lokaal;

import java.time.LocalDateTime;
import java.util.List;

public interface LokaalService {

    Lokaal createLokaal(Lokaal lokaal);

    Lokaal getLokaalById(Long lokaalId);

    List<Lokaal> getAllLokalen();

    Lokaal updateLokaal(Lokaal lokaal);

    void deleteLokaal(Long lokaalId);

    public List<Lokaal> getAvailableLokalenStartingFromDate(LocalDateTime startDate);
    public List<Lokaal> getAvailableLokalenUntilEndDate(LocalDateTime endDate);
    public List<Lokaal> getLokalenWithMinimumCapacity(int minimumCapacity);

}
