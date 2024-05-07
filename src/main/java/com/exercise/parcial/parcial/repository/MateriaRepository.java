package com.exercise.parcial.parcial.repository;

import com.exercise.parcial.parcial.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository <Materia, Integer> {
}
