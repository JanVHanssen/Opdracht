package Opdracht.Opdracht.controller;

import Opdracht.Opdracht.entity.Campus;
import Opdracht.Opdracht.entity.Lokaal;
import Opdracht.Opdracht.service.LokaalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/lokaal")
public class LokaalController {

    @Autowired
    private LokaalService lokaalService;

    @PostMapping("/create")
    public ResponseEntity<Lokaal> createLokaal(@RequestBody Lokaal lokaal) {
        Lokaal savedLokaal = lokaalService.createLokaal(lokaal);
        return new ResponseEntity<>(savedLokaal, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lokaal> getLokaalById(@PathVariable("id") Long lokaalId){
        Lokaal lokaal = lokaalService.getLokaalById(lokaalId);
        return new ResponseEntity<>(lokaal, HttpStatus.OK);
    }
    @GetMapping("/availableFrom")
    public ResponseEntity<List<Lokaal>> getAvailableFromLokalen(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime) {
        List<Lokaal> availableFromLokalen = lokaalService.getAvailableLokalenStartingFromDate(startDateTime);
        return new ResponseEntity<>(availableFromLokalen, HttpStatus.OK);
    }
    @GetMapping("/availableUntil")
    public ResponseEntity<List<Lokaal>> getAvailableUntilLokalen(
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        List<Lokaal> availableUntilLokalen = lokaalService.getAvailableLokalenUntilEndDate(endDateTime);
        return new ResponseEntity<>(availableUntilLokalen, HttpStatus.OK);
    }
    @GetMapping("/minNumberOfSeats")
    public ResponseEntity<List<Lokaal>> getLokalenWithMinimumCapacity(
            @RequestParam("minimumCapacity") int minimumCapacity) {
        List<Lokaal> lokalenWithMinimumCapacity = lokaalService.getLokalenWithMinimumCapacity(minimumCapacity);
        return new ResponseEntity<>(lokalenWithMinimumCapacity, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Lokaal>> getAllLokalen(){
        List<Lokaal> lokalen = lokaalService.getAllLokalen();
        return new ResponseEntity<>(lokalen, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lokaal> updateLokaal(@PathVariable("id") Long lokaalId,
                                               @RequestBody Lokaal lokaal){
        lokaal.setId(lokaalId);
        Lokaal updatedLokaal = lokaalService.updateLokaal(lokaal);
        return new ResponseEntity<>(updatedLokaal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLokaal(@PathVariable("id") Long lokaalId){
        lokaalService.deleteLokaal(lokaalId);
        return new ResponseEntity<>("Lokaal successfully deleted!", HttpStatus.OK);
    }
}
