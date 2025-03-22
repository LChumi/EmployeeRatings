package com.cumpleanos.calificaciones.service.implementation;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import com.cumpleanos.calificaciones.persistence.entities.Empleado;
import com.cumpleanos.calificaciones.persistence.repository.CalificacionRepository;
import com.cumpleanos.calificaciones.persistence.repository.CalificacionRepositoryCustom;
import com.cumpleanos.calificaciones.service.interfaces.ICalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CalificacionServiceImpl extends GenericServiceImpl<Calificacion, String> implements ICalificacionService {

    private final CalificacionRepository repository;
    private final CrudTxtClientServiceImpl crudTxtClient;
    private final CalificacionRepositoryCustom customRepository;

    @Override
    public CrudRepository<Calificacion, String> getRepository() {
        return repository;
    }

    @Override
    public Calificacion saved(Calificacion c) {
        if (c.getCliente() == null && null != c.getEmpleado()) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        } else if (c.getCliente().getNombre() == null){
            String nombre = crudTxtClient.getCliente(c.getId(), "C");
            if (nombre == null || nombre.isEmpty()){
                nombre = "";
            }
            c.getCliente().setNombre(nombre);
        }
        return repository.save(c);
    }

    @Override
    public List<Calificacion> findAllOrderByFechaDesc() {
        return repository.findAllByOrderByFechaDesc();
    }

    @Override
    public List<Calificacion> listByEmpleado(Empleado empleado) {
        return repository.findByEmpleadoOrderByFechaDesc(empleado);
    }

    @Override
    public List<Calificacion> listByFilters(String empleadoId, Short rating, LocalDate fechaInicio, LocalDate fechaFin) {
        return customRepository.findRatings(empleadoId, rating, fechaInicio, fechaFin);
    }
}
