package com.umg.daniloPortillo.ApiDoctorStrange.controllers;

import com.umg.daniloPortillo.ApiDoctorStrange.entities.Paciente;
import com.umg.daniloPortillo.ApiDoctorStrange.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Paciente crearPaciente (@RequestBody Paciente paciente){
        Paciente nuevoPaciente = pacienteRepository.save(paciente);
        return nuevoPaciente;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Collection<Paciente> getPacientes(){
        Iterable<Paciente> pacientes = pacienteRepository.findAll();
        return (Collection<Paciente>) pacientes;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void borrarPaciente(@PathVariable(name = "id") Long id){

        pacienteRepository.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Paciente buscarPaciente(@PathVariable(name = "id") Long id){
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        Paciente result = null;
        if(paciente.isPresent()){
            result = paciente.get();
        }
        return result;
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Paciente editarPaciente(@PathVariable(name = "id") Long id , @RequestBody Paciente paciente){
        Optional<Paciente> pacientesincambiar = pacienteRepository.findById(id);
        if(pacientesincambiar.isPresent()){
            Paciente actual = pacientesincambiar.get();
            actual.setId(id);
            actual.setPrimerNombre(paciente.getPrimerNombre());
            actual.setSegundoNombre(paciente.getSegundoNombre());
            actual.setApellido(paciente.getApellido());
            actual.setSegundoApellido(paciente.getSegundoApellido());
            actual.setEdad(paciente.getEdad());
            Paciente PacienteNuevo = pacienteRepository.save(actual);
            return PacienteNuevo;
        }
        return null;
    }


}
