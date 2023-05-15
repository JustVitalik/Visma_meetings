package com.example.task1.Service;

import com.example.task1.Entity.Meet;
import com.example.task1.Entity.ResponsiblePerson;
import com.example.task1.Entity.Users;
import com.example.task1.Repository.MeetRepository;
import com.example.task1.Repository.ResponsiblePersonRepository;
import com.example.task1.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetService {

    private final MeetRepository meetRepository;
    private final UserRepository userRepository;
    private final ResponsiblePersonRepository responsiblePersonRepository;

    public MeetService(MeetRepository meetRepository, UserRepository userRepository, ResponsiblePersonRepository responsiblePersonRepository) {
        this.meetRepository = meetRepository;
        this.userRepository = userRepository;
        this.responsiblePersonRepository = responsiblePersonRepository;
    }

    public List<Meet> allMeet() {
        return meetRepository.findAll();
    }

    public Optional<Meet> findMeetId(Long id) {
        return meetRepository.findById(id);

    }

    public void add(Meet meet) {
        meetRepository.save(meet);
    }

    public void deleteMeet(Long meet_id) {
        meetRepository.deleteById(meet_id);
    }

    public void deleteUserFromMeet(Long user_id) {
        Users users = userRepository.findById(user_id).get();
        userRepository.delete(users);
    }



    public Meet assignUsersPersonToMeet(Long users_id, Long meet_id, Long person_id) {
        List<Users> usersList = null;
        Users users = userRepository.findById(users_id).get();
        ResponsiblePerson responsiblePerson = responsiblePersonRepository.findById(person_id).get();
        Meet meet = meetRepository.findById(meet_id).get();
        meet.setResponsiblePerson(responsiblePerson);
        usersList = meet.getUsers();
        usersList.add(users);
        meet.setUsers(usersList);
        return meetRepository.save(meet);
    }


    public Page<Meet> getMeetPagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if (null != sortProperty) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
        } else {
            pageable = pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "name");
        }
        return meetRepository.findAll(pageable);
    }
}