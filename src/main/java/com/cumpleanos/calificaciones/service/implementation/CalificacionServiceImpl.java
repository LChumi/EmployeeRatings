package com.cumpleanos.calificaciones.service.implementation;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import com.cumpleanos.calificaciones.persistence.repository.CalificacionRepository;
import com.cumpleanos.calificaciones.persistence.repository.CalificacionRepositoryCustom;
import com.cumpleanos.calificaciones.service.interfaces.ICalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
        if (c.getCliente() == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        if (c.getCliente().getNombre() == null || c.getCliente().getNombre().isEmpty()) {
            String nombre = crudTxtClient.getCliente(c.getCliente().getId(), "C");
            c.getCliente().setNombre(nombre != null ? nombre : "");
        }
        c.registerHour();
        return repository.save(c);
    }


    @Override
    public List<Calificacion> findAllOrderByFechaDesc() {
        // Obtener el primer día del mes actual
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();

        // Obtener el último día del mes actual
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDate = calendar.getTime();

        return repository.findAllByFechaBetweenOrderByFechaDesc(startDate, endDate);
    }

    @Override
    public List<Calificacion> listByEmpleado(String empleado) {
        return repository.findByEmpleado_IdOrderByFechaDesc(empleado);
    }

    @Override
    public List<Calificacion> listByFilters(String empleadoId, Short rating, LocalDate fechaInicio, LocalDate fechaFin) {
        return customRepository.findRatings(empleadoId, rating, fechaInicio, fechaFin);
    }
}
