package com.exercise.parcial.parcial.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table (name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer materiaId;
    private String nombreMateria;
    private String  descripcionMateria;
    private int salonMateria;
    private Date horarioMateria;
    private String docenteMateria;
}
