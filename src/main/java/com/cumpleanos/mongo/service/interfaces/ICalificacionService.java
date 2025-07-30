package com.cumpleanos.mongo.service.interfaces;

import com.cumpleanos.mongo.persistence.entities.Calificacion;

import java.time.LocalDate;
import java.util.List;

public interface ICalificacionService extends IGenericService<Calificacion, String> {

    Calificacion saved(Calificacion calificacion);

    List<Calificacion> findAllOrderByFechaDesc();

    List<Calificacion> listByEmpleado(String empleado);

    List<Calificacion> listByFilters(String empleadoId, Short rating, LocalDate fechaInicio, LocalDate fechaFin);
}
