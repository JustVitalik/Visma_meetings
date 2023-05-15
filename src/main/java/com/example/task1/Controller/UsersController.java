package com.example.task1.Controller;

import com.example.task1.Entity.Users;
import com.example.task1.Repository.UserRepository;
import com.example.task1.Service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UsersController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/list")
    public ResponseEntity<List<Users>> allUsers() {
        List<Users> listUsers = userService.allUsers();
        if (listUsers != null && !listUsers.isEmpty()) {
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        Users user = userService.findUserId(id).get();
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity addUser(@RequestBody Users user) {
        try {
            if (userRepository.findByName(user.getName()) != null) {
                return ResponseEntity.badRequest().body("Users with the same name already exists");
            }
            userService.add(user);
            return ResponseEntity.ok("Successfully saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("Successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/page/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<Users> usersPage(@PathVariable Integer pageNumber,
                                 @PathVariable Integer pageSize,
                                 @PathVariable String sortProperty) {
        return userService.getUsersPagination(pageNumber, pageSize, sortProperty);
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public Page<Users> usersPage(@PathVariable Integer pageNumber,
                                 @PathVariable Integer pageSize) {
        return userService.getUsersPagination(pageNumber, pageSize, null);
    }


}
