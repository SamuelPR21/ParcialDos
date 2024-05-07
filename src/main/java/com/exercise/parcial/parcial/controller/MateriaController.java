package com.exercise.parcial.parcial.controller;

import com.exercise.parcial.parcial.model.Materia;
import com.exercise.parcial.parcial.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    //Lista los materias
    @GetMapping("/materias")
    public List<Materia> obtenerTodasLasMaterias(){
        return (List<Materia>) materiaRepository.findAll();
    }
    //Agrega materias
    @PostMapping("/materias/nuevo")
    public Materia agregamateria(@RequestBody Materia materia){
        return materiaRepository.save(materia);
    }

    //Editar materias
    @PatchMapping("/materias/editar/{id}")
    public Materia editarMaterias(@PathVariable Integer id, @RequestBody Materia materia){
        Optional<Materia> materiaExiste = materiaRepository.findById(id);
        if (materiaExiste.isPresent()){
            Materia materiaActual = materiaExiste.get();
            materiaActual.setNombreMateria(materia.getNombreMateria());
            materiaActual.setDescripcionMateria(materia.getDescripcionMateria());
            materiaActual.setSalonMateria(materia.getSalonMateria());
            materiaActual.setHorarioMateria(materia.getHorarioMateria());
            materiaActual.setDocenteMateria(materia.getDocenteMateria());
            return materiaRepository.save(materiaActual);
        }
        return null;
    }

    //Obtener materia por ID
    @GetMapping("/materias/{id}")
    public Materia obtenerMateriaPorId(@PathVariable Integer id){
        return materiaRepository.findById(id).orElse(null);
    }

    //Eliminar materia por id
    @DeleteMapping("/materias/{id}")
    public void elminarMateriaPorId(@PathVariable Integer id){
        materiaRepository.deleteById(id);
    }



}
