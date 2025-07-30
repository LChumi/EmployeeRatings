package com.cumpleanos.mongo.presentation.controller;

import com.cumpleanos.mongo.persistence.entities.Calificacion;
import com.cumpleanos.mongo.service.interfaces.ICalificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Calificaciones" , description = "Calificaciones de colaboradores")
public class RatingsController {

    private final ICalificacionService service;

    @GetMapping("/all")
    public ResponseEntity<List<Calificacion>> getAll() {
        List<Calificacion> calificaciones = service.findAllOrderByFechaDesc();
        return ResponseEntity.ok(calificaciones);
    }

    @Operation(summary = "Lista las calificaciones por empleado")
    @Parameter(name = "empleado", description = "ID del empleado" , in = ParameterIn.HEADER, required = true)
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
