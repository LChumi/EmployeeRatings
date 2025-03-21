package com.cumpleanos.calificaciones.service.interfaces;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import com.cumpleanos.calificaciones.persistence.entities.Empleado;

import java.util.List;

public interface CalificacionService extends IGenericService<Calificacion, String> {

    List<Calificacion> findAllOrderByFechaDesc();
    List<Calificacion> listByEmpleado(Empleado empleado);
}
