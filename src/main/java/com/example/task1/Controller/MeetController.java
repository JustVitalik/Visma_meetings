package com.example.task1.Controller;

import com.example.task1.Entity.Meet;
import com.example.task1.Entity.Users;
import com.example.task1.Repository.MeetRepository;
import com.example.task1.Service.MeetService;
import com.example.task1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meet")
public class MeetController {
    private final MeetService meetService;
    private final MeetRepository meetRepository;
    private final UserRepository userRepository;


    @Autowired
    public MeetController(MeetService meetService, MeetRepository meetRepository, UserRepository userRepository) {
        this.meetService = meetService;
        this.meetRepository = meetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Meet>> allMeet() {
        List<Meet> meetList = meetService.allMeet();
        if (meetList != null && !meetList.isEmpty()) {
            return new ResponseEntity<>(meetList, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/item/{id}")
    public void getMeet(@PathVariable Long id) {
        meetService.findMeetId(id);
    }

    @PostMapping
    public ResponseEntity addMeet(@RequestBody Meet meet) {
        try {
            meetService.add(meet);
            return ResponseEntity.ok("Successfully saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/{user_id}/{person_id}/meet/{meet_id}")
    public Meet assignUsersPersonToMeet(@PathVariable Long user_id,
                                        @PathVariable Long person_id,
                                        @PathVariable Long meet_id) {
        return meetService.assignUsersPersonToMeet(user_id, person_id, meet_id);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteMeet(@PathVariable Long id) {
        try {
            meetService.deleteMeet(id);
            return ResponseEntity.ok("Successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/user/{user_id}")
    public ResponseEntity deleteUserFromMeet(@PathVariable Long user_id) {
        try {
            meetService.deleteUserFromMeet(user_id);
            return ResponseEntity.ok("Successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }


    @GetMapping("/page/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<Meet> usersPage(@PathVariable Integer pageNumber,
                                @PathVariable Integer pageSize,
                                @PathVariable String sortProperty) {
        return meetService.getMeetPagination(pageNumber, pageSize, sortProperty);
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public Page<Meet> usersPage(@PathVariable Integer pageNumber,
                                @PathVariable Integer pageSize) {
        return meetService.getMeetPagination(pageNumber, pageSize, null);
    }

}
