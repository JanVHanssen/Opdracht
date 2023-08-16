package Opdracht.Opdracht.controller;

import Opdracht.Opdracht.entity.Lokaal;
import Opdracht.Opdracht.service.LokaalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LokaalController {


    private LokaalService lokaalService;

    @PostMapping("/lokaal/create")
    public ResponseEntity<Lokaal> createLokaal(@RequestBody Lokaal lokaal){
        Lokaal savedLokaal = lokaalService.createLokaal(lokaal);
        return new ResponseEntity<>(savedLokaal, HttpStatus.CREATED);
    }

    @GetMapping("/lokaal/{id}")
    public ResponseEntity<Lokaal> getLokaalById(@PathVariable("id") Long lokaalId){
        Lokaal lokaal = lokaalService.getLokaalById(lokaalId);
        return new ResponseEntity<>(lokaal, HttpStatus.OK);
    }

    @GetMapping("/lokaal/")
    public ResponseEntity<List<Lokaal>> getAllLokalen(){
        List<Lokaal> lokalen = lokaalService.getAllLokalen();
        return new ResponseEntity<>(lokalen, HttpStatus.OK);
    }

    @PutMapping("/lokaal/{id}")
    public ResponseEntity<Lokaal> updateLokaal(@PathVariable("id") Long lokaalId,
                                               @RequestBody Lokaal lokaal){
        lokaal.setId(lokaalId);
        Lokaal updatedLokaal = lokaalService.updateLokaal(lokaal);
        return new ResponseEntity<>(updatedLokaal, HttpStatus.OK);
    }

    @DeleteMapping("/lokaal/{id}")
    public ResponseEntity<String> deleteLokaal(@PathVariable("id") Long lokaalId){
        lokaalService.deleteLokaal(lokaalId);
        return new ResponseEntity<>("Lokaal successfully deleted!", HttpStatus.OK);
    }
}
