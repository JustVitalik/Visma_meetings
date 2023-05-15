package com.example.task1.Repository;

import com.example.task1.Entity.ResponsiblePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsiblePersonRepository extends JpaRepository<ResponsiblePerson, Long> {
    ResponsiblePerson findByName(String username);

}
