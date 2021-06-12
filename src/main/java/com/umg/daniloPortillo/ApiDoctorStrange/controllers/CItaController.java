package com.umg.daniloPortillo.ApiDoctorStrange.controllers;


import com.umg.daniloPortillo.ApiDoctorStrange.entities.Cita;
import com.umg.daniloPortillo.ApiDoctorStrange.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/citas")
public class CItaController {

    @Autowired
    CitaRepository citaRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Cita crearCita (@RequestBody Cita cita){
        Cita nuevaCita = citaRepository.save(cita);
        return nuevaCita;
    }


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Collection<Cita> getCitas(){
        Iterable<Cita> citas = citaRepository.findAll();
        return (Collection<Cita>) citas;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void borrrarCita(@PathVariable(name = "id") Long id){

        citaRepository.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Cita buscarCita(@PathVariable(name = "id") Long id){
        Optional<Cita> cita = citaRepository.findById(id);
        Cita result = null;
        if(cita.isPresent()){
            result = cita.get();
        }
        return result;
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Cita editarCita(@PathVariable(name = "id") Long id , @RequestBody Cita cita){
        Optional<Cita> citacambiar = citaRepository.findById(id);
        if(citacambiar.isPresent()){
            Cita actual = citacambiar.get();
            actual.setId(id);
            actual.setPaciente(cita.getId());
            actual.setEstado(cita.getEstado());
            actual.setHora(cita.getHora());
            actual.setObservaciones(cita.getObservaciones());
            Cita CitaNueva = citaRepository.save(actual);
            return CitaNueva;
        }
        return null;
    }


    @PutMapping(value = "/{id_cita}/aceptar")
    @Transactional
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Cita editAceptada(@PathVariable(name = "id_cita") Long id_cita , @RequestBody Cita cita){
        Optional<Cita> anterior = citaRepository.findById(id_cita);
        if (anterior.isPresent()){
            Cita nuevo = anterior.get();
            nuevo.setEstado("Aceptar");
            return citaRepository.save(nuevo);
        }
        return null;
    }

    @PutMapping(value = "/{id_cita}/cancelada")
    @Transactional
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Cita editCancelada(@PathVariable(name = "id_cita") Long id_cita , @RequestBody Cita cita){
        Optional<Cita> anterior = citaRepository.findById(id_cita);
        if (anterior.isPresent()){
            Cita nuevo = anterior.get();
            nuevo.setEstado("Cancelada");
            return citaRepository.save(nuevo);
        }
        return null;
    }

}
