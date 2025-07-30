package com.cumpleanos.mongo.persistence.repository;

import com.cumpleanos.mongo.persistence.entities.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CalificacionRepository extends MongoRepository<Calificacion, String> {

    List<Calificacion> findAllByOrderByFechaDesc();

    List<Calificacion> findAllByFechaBetweenOrderByFechaDesc(Date start, Date end);

    List<Calificacion> findByEmpleado_IdOrderByFechaDesc(String empleado);

}
