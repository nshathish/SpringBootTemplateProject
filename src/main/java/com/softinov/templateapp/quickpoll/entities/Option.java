package com.softinov.templateapp.quickpoll.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @Column(name = "option_value")
    private String value;


}
