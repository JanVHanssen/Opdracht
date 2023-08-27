package Opdracht.Opdracht.controller;

import Opdracht.Opdracht.entity.Campus;
import Opdracht.Opdracht.exception.CampusNotFoundException;
import Opdracht.Opdracht.service.CampusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    private CampusService campusService;


    @PostMapping("/create")
    public ResponseEntity<Campus> createCampus(@RequestBody Campus campus){
        Campus savedCampus = campusService.createCampus(campus);
        return new ResponseEntity<>(savedCampus, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campus> getCampusById(@PathVariable("id") Long campusId){
        Campus campus = campusService.getCampusById(campusId);
        return new ResponseEntity<>(campus, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Campus>> getAllCampus(){
        List<Campus> campus = campusService.getAllCampus();
        return new ResponseEntity<>(campus, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCampus(@PathVariable("id") Long campusId,
                                          @RequestBody Campus campus) {
        campus.setId(campusId);
        try {
            Campus updatedCampus = campusService.updateCampus(campus);
            return new ResponseEntity<>(updatedCampus, HttpStatus.OK);
        } catch (CampusNotFoundException ex) {
            String errorMessage = "Campus not found with ID: " + campusId;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampus(@PathVariable("id") Long campusId){
        campusService.deleteCampus(campusId);
        return new ResponseEntity<>("Campus successfully deleted!", HttpStatus.OK);
    }

}
