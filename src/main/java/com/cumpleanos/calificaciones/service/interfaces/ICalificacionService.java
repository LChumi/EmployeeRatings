package com.cumpleanos.calificaciones.service.interfaces;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import com.cumpleanos.calificaciones.persistence.entities.Empleado;

import java.time.LocalDate;
import java.util.List;

public interface ICalificacionService extends IGenericService<Calificacion, String> {

    Calificacion saved(Calificacion calificacion);

    List<Calificacion> findAllOrderByFechaDesc();

    List<Calificacion> listByEmpleado(Empleado empleado);

    List<Calificacion> listByFilters(String empleadoId, Short rating, LocalDate fechaInicio, LocalDate fechaFin);
}
