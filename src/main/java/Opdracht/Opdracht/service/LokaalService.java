package Opdracht.Opdracht.service;

import Opdracht.Opdracht.entity.Lokaal;

import java.util.List;

public interface LokaalService {

    Lokaal createLokaal(Lokaal lokaal);

    Lokaal getLokaalById(Long lokaalId);

    List<Lokaal> getAllLokalen();

    Lokaal updateLokaal(Lokaal lokaal);

    void deleteLokaal(Long lokaalId);
}
