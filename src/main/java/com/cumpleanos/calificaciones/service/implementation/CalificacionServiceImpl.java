package com.cumpleanos.calificaciones.service.implementation;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import com.cumpleanos.calificaciones.persistence.entities.Empleado;
import com.cumpleanos.calificaciones.persistence.repository.CalificacionRepository;
import com.cumpleanos.calificaciones.service.interfaces.ICalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CalificacionServiceImpl extends GenericServiceImpl<Calificacion, String> implements ICalificacionService {

    private final CalificacionRepository repository;

    @Override
    public CrudRepository<Calificacion, String> getRepository() {
        return repository;
    }

    @Override
    public List<Calificacion> findAllOrderByFechaDesc() {
        return repository.findAllByOrderByFechaDesc();
    }

    @Override
    public List<Calificacion> listByEmpleado(Empleado empleado) {
        return repository.findByEmpleadoOrderByFechaDesc(empleado);
    }
}
