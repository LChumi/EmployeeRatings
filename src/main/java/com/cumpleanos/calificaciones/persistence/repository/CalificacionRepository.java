package com.cumpleanos.calificaciones.persistence.repository;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import com.cumpleanos.calificaciones.persistence.entities.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends MongoRepository<Calificacion, String> {

    List<Calificacion> findAllByOrderByFechaDesc();

    List<Calificacion> findByEmpleadoOrderByFechaDesc(Empleado empleado);

}
