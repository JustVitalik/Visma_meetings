package com.example.task1.Entity;

import com.example.task1.Entity.Meet;

import javax.persistence.*;

@Entity
public class ResponsiblePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(mappedBy = "responsiblePerson", cascade = CascadeType.ALL)
    private Meet meet;

    public ResponsiblePerson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
