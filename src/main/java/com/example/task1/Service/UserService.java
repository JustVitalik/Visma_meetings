package com.example.task1.Service;

import com.example.task1.Entity.Users;
import com.example.task1.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<Users> allUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> findUserId(Long id) {
        return userRepository.findById(id);
    }


    public void add(Users user) {
        userRepository.save(user);
    }

    public void delete(Long id) {

        userRepository.deleteById(id);

    }

    public Page<Users> getUsersPagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if (null != sortProperty) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
        } else {
            pageable = pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "name");
        }

        return userRepository.findAll(pageable);
    }
}