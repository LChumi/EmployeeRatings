package com.cumpleanos.calificaciones.persistence.repository;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import com.cumpleanos.calificaciones.persistence.entities.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CalificacionRepository extends MongoRepository<Calificacion, String> {

    @Query(sort = "{ 'fecha' : -1 }")
    List<Calificacion> findAllByOrderByFechaDesc();

    List<Calificacion> findByEmpleadoOrderByFechaDesc(Empleado empleado);
}
