package com.example.task1.Controller;

import com.example.task1.Entity.ResponsiblePerson;
import com.example.task1.Entity.Users;
import com.example.task1.Repository.ResponsiblePersonRepository;
import com.example.task1.Service.ResponsiblePersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class ResponsiblePersonController {


    private final ResponsiblePersonRepository responsiblePersonRepository;
    private final ResponsiblePersonService responsiblePersonService;

    public ResponsiblePersonController(ResponsiblePersonRepository responsiblePersonRepository, ResponsiblePersonService responsiblePersonService) {
        this.responsiblePersonRepository = responsiblePersonRepository;
        this.responsiblePersonService = responsiblePersonService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponsiblePerson>> allPerson() {
        List<ResponsiblePerson> listPerson = responsiblePersonService.allPerson();
        if (listPerson != null && !listPerson.isEmpty()) {
            return new ResponseEntity<>(listPerson, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ResponsiblePerson> getPerson(@PathVariable Long id) {
        ResponsiblePerson responsiblePerson = responsiblePersonService.findPersonId(id).get();
        if (responsiblePerson != null) {
            return new ResponseEntity<>(responsiblePerson, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addPerson(@RequestBody ResponsiblePerson responsiblePerson) {
        try {
            if (responsiblePersonRepository.findByName(responsiblePerson.getName()) != null) {
                return ResponseEntity.badRequest().body("Users with the same name already exists");
            }
            responsiblePersonService.add(responsiblePerson);
            return ResponseEntity.ok("Successfully saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        try {
            if (true) {
                responsiblePersonService.delete(id);
            }

            return ResponseEntity.ok("Successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
