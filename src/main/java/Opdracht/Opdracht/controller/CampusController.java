package Opdracht.Opdracht.controller;

import Opdracht.Opdracht.entity.Campus;
import Opdracht.Opdracht.service.CampusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CampusController {

    private CampusService campusService;

    @PostMapping("/campus/create")
    public ResponseEntity<Campus> createCampus(@RequestBody Campus campus){
        Campus savedCampus = campusService.createCampus(campus);
        return new ResponseEntity<>(savedCampus, HttpStatus.CREATED);
    }

    @GetMapping("/campus/{id}")
    public ResponseEntity<Campus> getCampusById(@PathVariable("id") Long campusId){
        Campus campus = campusService.getCampusById(campusId);
        return new ResponseEntity<>(campus, HttpStatus.OK);
    }

    @GetMapping("/campus/")
    public ResponseEntity<List<Campus>> getAllCampus(){
        List<Campus> campus = campusService.getAllCampus();
        return new ResponseEntity<>(campus, HttpStatus.OK);
    }

    @PutMapping("/campus/{id}")
    public ResponseEntity<Campus> updateCampus(@PathVariable("id") Long campusId,
                                           @RequestBody Campus campus){
        campus.setId(campusId);
        Campus updatedCampus = campusService.updateCampus(campus);
        return new ResponseEntity<>(updatedCampus, HttpStatus.OK);
    }

    @DeleteMapping("/campus/{id}")
    public ResponseEntity<String> deleteCampus(@PathVariable("id") Long campusId){
        campusService.deleteCampus(campusId);
        return new ResponseEntity<>("Campus successfully deleted!", HttpStatus.OK);
    }
}
