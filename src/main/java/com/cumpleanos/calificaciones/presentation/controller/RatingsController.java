package com.cumpleanos.calificaciones.presentation.controller;

import com.cumpleanos.calificaciones.persistence.entities.Calificacion;
import com.cumpleanos.calificaciones.persistence.entities.Empleado;
import com.cumpleanos.calificaciones.service.interfaces.ICalificacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("ratings")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RatingsController {

    private final ICalificacionService service;

    @GetMapping("/all")
    public ResponseEntity<List<Calificacion>> getAll() {
        List<Calificacion> calificaciones = service.findAllOrderByFechaDesc();
        return ResponseEntity.ok(calificaciones);
    }

    @GetMapping("/all/employee/{empleado}")
    public ResponseEntity<List<Calificacion>> getAllEmployee(@PathVariable String empleado) {
        List<Calificacion> calificaciones = service.listByEmpleado(empleado);
        return  ResponseEntity.ok(calificaciones);
    }

    @PostMapping("/save")
    public ResponseEntity<Calificacion> save(@RequestBody Calificacion calificacion) {
        Calificacion cal =service.saved(calificacion);
        return ResponseEntity.ok(cal);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Calificacion cal = service.findById(id);
        if (cal==null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Calificacion>> filtros(
            @RequestParam(required = false) String empleadoId,
            @RequestParam(required = false) Short rating,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin
    ){
        List<Calificacion> calificaciones = service.listByFilters(empleadoId, rating, fechaInicio, fechaFin);
        return ResponseEntity.ok(calificaciones);
    }
}
