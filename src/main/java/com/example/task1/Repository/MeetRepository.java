package com.example.task1.Repository;

import com.example.task1.Entity.Meet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetRepository extends JpaRepository<Meet, Long> {

    List<Meet> findAll();


}
