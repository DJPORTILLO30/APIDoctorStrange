package com.umg.daniloPortillo.ApiDoctorStrange.repositories;

import com.umg.daniloPortillo.ApiDoctorStrange.entities.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteRepository extends CrudRepository<Paciente,Long> {
}
