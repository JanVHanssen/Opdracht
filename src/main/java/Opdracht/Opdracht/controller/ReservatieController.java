package Opdracht.Opdracht.controller;

import Opdracht.Opdracht.entity.Reservatie;
import Opdracht.Opdracht.service.ReservatieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ReservatieController {


    private ReservatieService reservatieService;

    @PostMapping("/reservatie/create")
    public ResponseEntity<Reservatie> createReservatie(@RequestBody Reservatie reservatie){
        Reservatie savedReservatie = reservatieService.createReservatie(reservatie);
        return new ResponseEntity<>(savedReservatie, HttpStatus.CREATED);
    }

    @GetMapping("/reservatie/{id}")
    public ResponseEntity<Reservatie> getReservatieById(@PathVariable("id") Long reservatieId){
        Reservatie reservatie = reservatieService.getReservatieById(reservatieId);
        return new ResponseEntity<>(reservatie, HttpStatus.OK);
    }

    @GetMapping("/reservatie/")
    public ResponseEntity<List<Reservatie>> getAllReservatie(){
        List<Reservatie> reservaties = reservatieService.getAllReservaties();
        return new ResponseEntity<>(reservaties, HttpStatus.OK);
    }

    @PutMapping("/reservatie/{id}")
    public ResponseEntity<Reservatie> updateReservatie(@PathVariable("id") Long reservatieId,
                                               @RequestBody Reservatie reservatie){
        reservatie.setId(reservatieId);
        Reservatie updatedReservatie = reservatieService.updateReservaties(reservatie);
        return new ResponseEntity<>(updatedReservatie, HttpStatus.OK);
    }

    @DeleteMapping("/reservatie/{id}")
    public ResponseEntity<String> deleteReservatie(@PathVariable("id") Long reservatieId){
        reservatieService.deleteReservatie(reservatieId);
        return new ResponseEntity<>("Reservatie successfully deleted!", HttpStatus.OK);
    }
}
