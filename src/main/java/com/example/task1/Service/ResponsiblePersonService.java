package com.example.task1.Service;

import com.example.task1.Entity.ResponsiblePerson;
import com.example.task1.Entity.Users;
import com.example.task1.Repository.ResponsiblePersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsiblePersonService {

    private final ResponsiblePersonRepository responsiblePersonRepository;

    public ResponsiblePersonService(ResponsiblePersonRepository responsiblePersonRepository) {
        this.responsiblePersonRepository = responsiblePersonRepository;
    }

    public List<ResponsiblePerson> allPerson() {
        return responsiblePersonRepository.findAll();
    }

    public Optional<ResponsiblePerson> findPersonId(Long id) {
        return responsiblePersonRepository.findById(id);
    }


    public void add(ResponsiblePerson responsiblePerson) {
        responsiblePersonRepository.save(responsiblePerson);
    }

    public void delete(Long id) {
        responsiblePersonRepository.deleteById(id);
    }
}