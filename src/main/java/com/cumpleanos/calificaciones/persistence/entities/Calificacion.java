package com.cumpleanos.calificaciones.persistence.entities;

import com.cumpleanos.calificaciones.utils.CalificacionEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@Document(collection = "calificacion")
public class Calificacion  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @CreatedDate
    private Date fecha;

    @CreatedDate
    private LocalTime hora;

    private Cliente cliente;
    private Empleado empleado;
    private String observacion;
    private CalificacionEnum calificacion;
    private Integer rating;
}
