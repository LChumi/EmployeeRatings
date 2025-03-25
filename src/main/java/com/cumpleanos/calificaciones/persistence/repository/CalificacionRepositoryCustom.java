package com.cumpleanos.calificaciones.persistence.repository;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CalificacionRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public List<Calificacion> findRatings(String empleadoId, Short rating, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Criteria> filters = new ArrayList<>();

        if (empleadoId != null) {
            filters.add(Criteria.where("empleado.id").is(empleadoId));
        }

        if (rating != null) {
            filters.add(Criteria.where("rating").is(rating));
        }

        if (fechaInicio != null && fechaFin != null) {
            filters.add(Criteria.where("fecha").gte(fechaInicio).lte(fechaFin));
        }

        if (fechaInicio != null) {
            LocalDateTime startOfDay = fechaInicio.atStartOfDay(); // 2025-03-24T00:00:00
            LocalDateTime endOfDay = fechaInicio.atTime(LocalTime.MAX); // 2025-03-24T23:59:59

            filters.add(Criteria.where("fecha").gte(startOfDay).lt(endOfDay));
        }

        Query query = new Query();
        if (!filters.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(filters.toArray(new Criteria[0])));
        }

        return mongoTemplate.find(query, Calificacion.class);
    }

}
